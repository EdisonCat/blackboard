package com.edison.blackboard.api;

import com.edison.blackboard.model.Person;
import com.edison.blackboard.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/blackboard/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path = "{type}")
    public void addPerson(@Valid @NotNull @RequestBody Person person, @PathVariable("type") String personType) {
        personService.addPerson(person, personType);
    }

    @GetMapping(path = "{type}")
    public List<Person> getAllPeople(@PathVariable("type") String personType) {
        return personService.getAllPeople(personType);
    }

    @GetMapping(path = "{type}/{id}")
    public Person getPersonById(@PathVariable("id") UUID id, @PathVariable("type") String personType) {
        return personService.getPersonById(id, personType).orElse(null);
    }

    @PutMapping(path = "{type}/{id}")
    public boolean updatePersonById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person person,
                                    @PathVariable("type") String personType) {
        return personService.updatePersonById(id, new Person(id, person.getName(), person.getProgram()), personType);
    }

    @DeleteMapping(path = "{type}/{id}")
    public boolean deletePersonById(@PathVariable("id") UUID id, @PathVariable("type") String personType) {
        return personService.deletePersonById(id, personType);
    }
}
