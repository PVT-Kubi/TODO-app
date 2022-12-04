package bubi.kubi.Backend.repository;

import bubi.kubi.Backend.entity.Person;
import bubi.kubi.Backend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
