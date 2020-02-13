package com.edison.blackboard.dao;

import com.edison.blackboard.model.Course;
import com.edison.blackboard.model.Person;
import com.edison.blackboard.model.Professor;
import com.edison.blackboard.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.edison.blackboard.dao.ProgramDao.getProgramByIndex;

@Repository("PersonDao")
public class PersonDao {
    private static List<Person> studentList = new ArrayList<>();
    private static List<Person> professorList = new ArrayList<>();
    static {
        //add students
        studentList.add(new Student(UUID.randomUUID(),"Larry", getProgramByIndex(2)));
        studentList.add(new Student(UUID.randomUUID(),"Edison",getProgramByIndex(0)));

        //add professors
        professorList.add(new Professor(UUID.randomUUID(), "Tom", getProgramByIndex(0)));
        professorList.add(new Professor(UUID.randomUUID(), "Riddle", getProgramByIndex(0)));
        professorList.get(0).addCourse(new Course(UUID.randomUUID(), "History of Magic"));
        professorList.get(0).addCourse(new Course(UUID.randomUUID(), "Potions"));
        professorList.get(1).addCourse(new Course(UUID.randomUUID(), "Defense Against the Dark Arts"));
        professorList.get(1).addCourse(new Course(UUID.randomUUID(), "Herbology"));
    }

    public boolean addPerson(Person person, String personType) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person, personType);
    }

    private boolean insertPerson(UUID id, Person person, String personType) {
        if(personType.equals("student")) {
            studentList.add(new Person(id, person.getName(), person.getProgram()));
            return true;
        }
        else if(personType.equals("professor")) {
            professorList.add(new Person(id, person.getName(), person.getProgram()));
            return true;
        }
        return false;
    }

    public List<Person> selectAllStudents() {
        return studentList;
    }

    public List<Person> selectAllProfessors() {
        return professorList;
    }

    public boolean deletePersonById(UUID id, String personType) {
        Optional<Person> toBeDeleted = selectPersonById(id, personType);
        if(toBeDeleted.isEmpty()) return false;
        if(personType.equals("student"))
            studentList.remove(toBeDeleted.get());
        else if(personType.equals("professor"))
            professorList.remove(toBeDeleted.get());
        return true;
    }

    public boolean updatePersonById(UUID id, Person person, String personType) {
        Person newPerson = new Person(id, person.getName(), person.getProgram());
        return selectPersonById(id, personType).map(person1 -> {
            int index;
            if(personType.equals("student")) {
                index = studentList.indexOf(person1);
                if(index >= 0) {
                    studentList.set(index, newPerson);
                    return true;
                }
                return false;
            }
            else if(personType.equals("professor")) {
                index = professorList.indexOf(person1);
                if(index >= 0) {
                    professorList.set(index, newPerson);
                    return true;
                }
                return false;
            }
            return false;
        }).orElse(false);
    }

    public Optional<Person> selectPersonById(UUID id, String personType) {
        if(personType.equals("student"))
            return studentList.stream().filter(person -> person.getId().equals(id)).findFirst();
        else if(personType.equals("professor"))
            return professorList.stream().filter(person -> person.getId().equals(id)).findFirst();
        return Optional.empty();
    }
}
