package com.edison.blackboard.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Course {
    private String name;
    private final UUID id;
    private Set<Student> studentSet;
    private Student ta;
    private Professor professor;

    public Course(UUID id) {
        this.id = id;
        this.studentSet = new HashSet<>();
    }

    public Course(UUID id, String name) {
        this.id = id;
        setName(name);
        this.studentSet = new HashSet<>();
    }

    public Course(UUID id, Course course) {
        this.id = id;
        this.professor = course.getProfessor();
        this.ta = course.getTa();
        this.studentSet = course.getStudentSet();
        this.name = course.getName();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        this.studentSet.add(student);
    }

    public void removeCourse(Student student) {
        this.studentSet.remove(student);
    }

    public Set<Student> getStudentSet() {
        return this.studentSet;
    }

    public void setTa(Student student) {
        this.ta = student;
    }

    public Student getTa() {
        return this.ta;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return this.professor;
    }

}
