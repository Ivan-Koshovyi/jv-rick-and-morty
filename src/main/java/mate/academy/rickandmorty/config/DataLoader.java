package mate.academy.rickandmorty.config;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.repository.PersonRepository;
import mate.academy.rickandmorty.service.RickAndMortyApiClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RickAndMortyApiClient rickAndMortyApiClient;
    private final PersonRepository personRepository;

    @Override
    public void run(String... args) {
        if (personRepository.count() > 0) {
            System.out.println("DB have info for person. Skipping API load.");
            return;
        }

        System.out.println("Database empty. Loading data from API...");
        rickAndMortyApiClient.getInfo();
    }
}
