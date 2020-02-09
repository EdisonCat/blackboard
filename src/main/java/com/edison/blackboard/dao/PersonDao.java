package com.edison.blackboard.dao;

import com.edison.blackboard.model.Person;
import java.util.List;
import java.util.UUID;

public interface PersonDao {
    boolean insertPerson(UUID id, Person person);

    List<Person> selectAllPeople();

    default boolean addPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    boolean deletePersonById(UUID id);

    boolean updatePersonById(UUID id);

    Person selectPersonById(UUID id);
}
