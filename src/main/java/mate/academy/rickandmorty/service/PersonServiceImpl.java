package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ApiCharacterDto;
import mate.academy.rickandmorty.dto.PersonDto;
import mate.academy.rickandmorty.mapper.PersonMapper;
import mate.academy.rickandmorty.model.Person;
import mate.academy.rickandmorty.repository.PersonRepository;
import org.springframework.data.domain.PageRequest;
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
        long count = personRepository.count();
        if (count == 0) {
            throw new IllegalStateException("No persons found");
        }
        Random random = new Random();
        int index = random.nextInt((int) count);

        Person person = personRepository
                .findAll(PageRequest.of(index, 1))
                .getContent()
                .get(0);

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
