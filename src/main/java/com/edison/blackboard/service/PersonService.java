package com.edison.blackboard.service;

import com.edison.blackboard.dao.PersonDao;
import com.edison.blackboard.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("PersonDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public boolean addPerson(Person person) {
        return personDao.addPerson(person);
    }

    public List<Person> getAllStudents() {
        return personDao.selectAllStudents();
    }

    public List<Person> getAllProfessors() {
        return personDao.selectAllProfessors();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public boolean deletePersonById(UUID id) {
        return personDao.deletePersonById(id);
    }

    public boolean updatePersonById(UUID id, Person person) {
        return personDao.updatePersonById(id, person);
    }
}
