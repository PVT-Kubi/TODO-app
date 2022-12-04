package bubi.kubi.Backend.repository;

import bubi.kubi.Backend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
