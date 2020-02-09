package com.edison.blackboard.dao;

import com.edison.blackboard.model.Person;
import com.edison.blackboard.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("FakeStudentDao")
public class FakeStudentDataAccessService implements PersonDao {
    private static List<Student> studentList = new ArrayList<>();

    @Override
    public boolean insertPerson(UUID id, Person person) {
        studentList.add(new Student(id, person.getName()));
        return true;
    }

    public List<Student> selectAllPeople() {
        return studentList;
    }


    @Override
    public boolean deletePersonById(UUID id) {
        Optional<Student> toBeDeleted = selectPersonById(id);
        if(toBeDeleted.isEmpty()) return false;
        studentList.remove(toBeDeleted.get());
        return true;
    }

    @Override
    public boolean updatePersonById(UUID id, Person person) {
        Student newStudent = new Student(id, person.getName());
        return selectPersonById(id).map(person1 -> {
            int index = studentList.indexOf(person1);
            if(index >= 0) {
                studentList.set(index, newStudent);
                return true;
            }
            return false;
        }).orElse(false);
    }

    public Optional<Student> selectPersonById(UUID id) {
        return studentList.stream().filter(person -> person.getId().equals(id)).findFirst();
    }
}
