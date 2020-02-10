package com.edison.blackboard.dao;

import com.edison.blackboard.model.Person;
import com.edison.blackboard.model.Professor;
import com.edison.blackboard.model.Program;
import com.edison.blackboard.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("PersonDao")
public class PersonDao {
    private static List<Person> personList = new ArrayList<>();
    static {
        personList.add(new Student(UUID.randomUUID(),"Larry", new Program("Information Systems")));
        personList.add(new Student(UUID.randomUUID(),"Edison", new Program("Information Systems")));
    }

    public boolean addPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    public boolean insertPerson(UUID id, Person person) {
        personList.add(new Person(id, person.getName(), person.getProgram()));
        return true;
    }

    public List<Person> selectAllPeople() {
        return personList;
    }

    public boolean deletePersonById(UUID id) {
        Optional<Person> toBeDeleted = selectPersonById(id);
        if(toBeDeleted.isEmpty()) return false;
        personList.remove(toBeDeleted.get());
        return true;
    }

    public boolean updatePersonById(UUID id, Person person) {
        Person newPerson = new Person(id, person.getName(), person.getProgram());
        return selectPersonById(id).map(person1 -> {
            int index = personList.indexOf(person1);
            if(index >= 0) {
                personList.set(index, newPerson);
                return true;
            }
            return false;
        }).orElse(false);
    }

    public Optional<Person> selectPersonById(UUID id) {
        return personList.stream().filter(person -> person.getId().equals(id)).findFirst();
    }
}
