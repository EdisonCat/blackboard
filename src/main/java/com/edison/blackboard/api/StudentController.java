package com.edison.blackboard.api;

import com.edison.blackboard.model.Person;
import com.edison.blackboard.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/blackboard/student")
@RestController
public class StudentController {
    private final PersonService personService;

    @Autowired
    public StudentController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    @PutMapping(path = "{id}")
    public boolean updatePersonById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person person) {
        return personService.updatePersonById(id, new Person(id, person.getName(), person.getProgram()));
    }

    @DeleteMapping(path = "{id}")
    public boolean deletePersonById(@PathVariable("id") UUID id) {
        return personService.deletePersonById(id);
    }
}
