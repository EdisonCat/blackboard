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

    public Course() {
        this.id = UUID.randomUUID();
        this.studentSet = new HashSet<>();
    }

    public Course(String name) {
        setName(name);
        this.id = UUID.randomUUID();
        this.studentSet = new HashSet<>();
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
