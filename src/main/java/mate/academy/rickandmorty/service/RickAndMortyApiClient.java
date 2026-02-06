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

    public void getAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        String nextUrl = URL;

        while (nextUrl != null) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(nextUrl))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 429) {
                    System.out.println("Rate limit reached. Waiting 2 seconds...");
                    Thread.sleep(2000);
                    continue;
                }

                if (response.statusCode() != 200) {
                    System.err.println("API returned error: " + response.body());
                    break;
                }
                ApiCharacterResponseDto apiCharacterResponseDto = objectMapper.readValue(
                        response.body(),
                        new TypeReference<ApiCharacterResponseDto>() {}
                );
                apiCharacterResponseDto.results().forEach(personService::save);
                nextUrl = apiCharacterResponseDto.info().next();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("All data successfully saved in DB");
    }
}
