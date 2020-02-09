package com.edison.blackboard.dao;

import com.edison.blackboard.model.Person;
import java.util.UUID;

public interface PersonDao {
    boolean insertPerson(UUID id, Person person);

    default boolean addPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    boolean deletePersonById(UUID id);

    boolean updatePersonById(UUID id, Person person);

}
