package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ApiCharacterDto;
import mate.academy.rickandmorty.dto.PersonDto;
import mate.academy.rickandmorty.mapper.PersonMapper;
import mate.academy.rickandmorty.model.Person;
import mate.academy.rickandmorty.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonDto save(ApiCharacterDto personDto) {
        Person person = personMapper.toModel(personDto);
        return personMapper.toDto(personRepository.save(person));
    }

    @Override
    public PersonDto getRandomPerson() {
        Random random = new Random();
        int i = random.nextInt(personRepository.findAll().size());
        Person person = personRepository.findAll().get(i);
        return personMapper.toDto(person);
    }

    @Override
    public List<PersonDto> getPersonsByName(String name) {
        return personRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(personMapper::toDto)
                .toList();
    }

}
