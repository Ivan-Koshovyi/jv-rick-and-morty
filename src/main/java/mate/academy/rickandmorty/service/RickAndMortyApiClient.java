package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ApiCharacterResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyApiClient {
    public static final String URL = "https://rickandmortyapi.com/api/character";
    private final PersonService personService;

    public void getInfo() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();
        try {
            HttpResponse<String> response
                    = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            ApiCharacterResponseDto apiCharacterResponseDto
                    = objectMapper.readValue(
                            response.body(),
                            new TypeReference<ApiCharacterResponseDto>() {}
            );
            apiCharacterResponseDto.results()
                    .forEach(personService::save);
            System.out.println("All data successful put in DB");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
