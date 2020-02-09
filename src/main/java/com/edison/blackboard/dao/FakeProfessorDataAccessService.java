package com.edison.blackboard.dao;

import com.edison.blackboard.model.Person;
import com.edison.blackboard.model.Professor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("FakeProfessorDao")
public class FakeProfessorDataAccessService implements PersonDao {
    private static List<Professor> professorList = new ArrayList<>();
    @Override
    public boolean insertPerson(UUID id, Person person) {
        professorList.add(new Professor(id, person.getName()));
        return true;
    }

    public List<Professor> selectAllPeople() {
        return professorList;
    }

    @Override
    public boolean deletePersonById(UUID id) {
        Optional<Professor> toBeDeleted = selectPersonById(id);
        if(toBeDeleted.isEmpty()) return false;
        professorList.remove(toBeDeleted.get());
        return true;
    }

    @Override
    public boolean updatePersonById(UUID id, Person person) {
        return false;
    }

    public Optional<Professor> selectPersonById(UUID id) {
        return professorList.stream().filter(person -> person.getId().equals(id)).findFirst();
    }
}
