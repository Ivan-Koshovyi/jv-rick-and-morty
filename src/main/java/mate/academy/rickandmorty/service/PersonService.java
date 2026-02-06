package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.ApiCharacterDto;
import mate.academy.rickandmorty.dto.PersonDto;

public interface PersonService {

    PersonDto save(ApiCharacterDto personDto);

    PersonDto getRandomPerson();

    List<PersonDto> getPersonsByName(String name);
}
