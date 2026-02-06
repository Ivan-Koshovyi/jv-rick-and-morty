package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.PersonDto;
import mate.academy.rickandmorty.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rickAndMorty")
@Tag(name = "Rick and Morty API", description = "Operations with Rick and Morty characters")
public class Controller {

    private final PersonService personService;

    @Operation(
            summary = "Get random Rick and Morty character",
            description = "Returns a random character from the local database"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Character successfully returned")
    })
    @GetMapping
    public PersonDto getRandomPersonOfRickAndMorty() {
        return personService.getRandomPerson();
    }

    @Operation(
            summary = "Search characters by name",
            description = "Returns a list of characters whose name contains the given string"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Characters successfully found")
    })
    @GetMapping("/search")
    public List<PersonDto> search(@RequestParam String name) {
        return personService.getPersonsByName(name);
    }
}
