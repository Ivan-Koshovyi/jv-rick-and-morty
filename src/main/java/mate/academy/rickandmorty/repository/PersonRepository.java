package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByNameContainingIgnoreCase(String name);
}
