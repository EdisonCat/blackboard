package com.edison.blackboard.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Person {
    private String name;
    private UUID id;
    private Set<Course> courseSet;
    private Program program;
    public Person(UUID id, String name) {
        this.courseSet = new HashSet<>();
        this.id = id;
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public UUID getId() {
        return this.id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addCourse(Course course) {
        this.courseSet.add(course);
    }
    public void removeCourse(Course course) {
        this.courseSet.remove(course);
    }
    public boolean isRegistered(Course course) {
        return this.courseSet.contains(course);
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
