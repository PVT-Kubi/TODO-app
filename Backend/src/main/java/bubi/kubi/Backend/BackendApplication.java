package bubi.kubi.Backend;


import bubi.kubi.Backend.entity.Person;
import bubi.kubi.Backend.entity.Todo;
import bubi.kubi.Backend.repository.PersonRepository;
import bubi.kubi.Backend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;


	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Person person = new Person();
		person.setUsername("Kubi");
		person.setPassword("Maslo");

		Todo todo = new Todo();
		todo.setContent("End this project till tomorrow");

		person.getTodoList().add(todo);

		todoRepository.save(todo);
		personRepository.save(person);
	}
}