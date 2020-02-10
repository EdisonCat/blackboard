package com.edison.blackboard.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import com.edison.blackboard.dao.ProgramDao;

public class Program {
    private String name;
    private UUID id;
    private Set<Student> studentSet;
    private Set<Professor> professorSet;
    private Set<Course> courseSet;

    public Program(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.courseSet = new HashSet<>();
        this.studentSet = new HashSet<>();
        this.professorSet = new HashSet<>();
    }

    public Program(UUID id, Program program) {
        Optional<Program> newProgram = ProgramDao.selectProgramById(id);
        if(newProgram.isEmpty()) {
            System.out.println("Failed when updating program");
            return ;
        }
        this.name = newProgram.get().getName();
        this.id = id;
        if(program.courseSet != null)
            this.courseSet = program.getCourseList();
        if(program.studentSet != null)
         this.studentSet = program.getStudentList();
        if(program.professorSet != null)
            this.professorSet = program.getProfessorList();
    }

    public Program() {
        this.courseSet = new HashSet<>();
        this.studentSet = new HashSet<>();
        this.professorSet = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudentList() {
        return this.studentSet;
    }

    public Set<Professor> getProfessorList() {
        return professorSet;
    }

    public Set<Course> getCourseList() {
        return courseSet;
    }

    public void addCourse(Course course) {
        this.courseSet.add(course);
    }

    public void addStudent(Student student) {
        this.studentSet.add(student);
    }

    public void addProfessor(Professor professor) {
        this.professorSet.add(professor);
    }

    public UUID getId() {
        return id;
    }
}
