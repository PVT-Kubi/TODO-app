package bubi.kubi.Backend.controller;

import bubi.kubi.Backend.entity.Person;
import bubi.kubi.Backend.entity.Todo;
import bubi.kubi.Backend.repository.PersonRepository;
import bubi.kubi.Backend.repository.TodoRepository;
import bubi.kubi.Backend.request.AddPersonRequest;
import bubi.kubi.Backend.request.AddTodoRequest;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/persons")
public class personController {

    private PersonRepository personRepository;
    private TodoRepository todoRepository;



    public personController(PersonRepository personRepository, TodoRepository todoRepository) {
        this.personRepository = personRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/{personId}")
    public Person getPersonById(@PathVariable Long personId) {
        return personRepository.findById(personId).orElseThrow(() -> new NoSuchElementException());
    }

    @GetMapping("/{personName}/wichPerson")
    public Person getPersonByUserName(@PathVariable String personName) {
        List<Person> persons = personRepository.findAll();
        for (Person p: persons) {
            System.out.println(p.getUsername());
            if(p.getUsername().equals(personName)){
                System.out.println("found");
                return p;
            }
        }
        System.out.println("not found");
        return null;
    }    

    @PostMapping
    public Person addPerson(@RequestBody AddPersonRequest personRequest){
        Person person = new Person();
        person.setUsername(personRequest.getUsername());
        person.setPassword(personRequest.getPassword());
        return personRepository.save(person);
    }


    @PostMapping("/{personId}/todos")
    public void addTodo(@PathVariable Long personId, @RequestBody AddTodoRequest todoRequest) {
        Person person = personRepository.findById(personId).orElseThrow(() -> new NoSuchElementException());
        Todo todo = new Todo();
        todo.setContent(todoRequest.getContent());
        person.getTodoList().add(todo);
        todoRepository.save(todo);
        personRepository.save(person);
    }

    @PostMapping("/todos/{todoId}")
    public void toggleCompleted(@PathVariable Long todoId){
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        todo.setComplete(!todo.getComplete());
        todoRepository.save(todo);
    }


    @DeleteMapping("{personId}/todos/{todoId}")
    public void deleteTodo( @PathVariable Long personId, @PathVariable Long todoId){
        Person person = personRepository.findById(personId).orElseThrow(() -> new NoSuchElementException());
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        person.getTodoList().remove(todo);
        todoRepository.delete(todo);
    }

    @DeleteMapping("/{personId}")
    public void deletePerson(@PathVariable Long personId){
        Person person = personRepository.findById(personId).orElseThrow(() -> new NoSuchElementException());
        personRepository.delete(person);
    }
}
