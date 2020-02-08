package com.edison.blackboard.model;

import java.util.HashSet;
import java.util.Set;

public class Program {
    private String name;
    private Set<Student> studentSet;
    private Set<Professor> professorSet;
    private Set<Course> courseSet;

    public Program(String name) {
        this.name = name;
        this.courseSet = new HashSet<>();
        this.studentSet = new HashSet<>();
        this.professorSet = new HashSet<>();
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

}
