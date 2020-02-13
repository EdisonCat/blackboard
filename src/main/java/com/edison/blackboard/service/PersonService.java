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

    public boolean addPerson(Person person, String type) {
        return personDao.addPerson(person, type);
    }

    public List<Person> getAllPeople(String personType) {
        if(personType.equals("student")) return personDao.selectAllStudents();
        else if(personType.equals("professor")) return personDao.selectAllProfessors();
        return null;
    }

    public Optional<Person> getPersonById(UUID id, String personType) {
        return personDao.selectPersonById(id, personType);
    }

    public boolean deletePersonById(UUID id, String personType) {
        return personDao.deletePersonById(id, personType);
    }

    public boolean updatePersonById(UUID id, Person person, String personType) {
        return personDao.updatePersonById(id, person, personType);
    }
}
