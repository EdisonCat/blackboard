package com.edison.blackboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Person {
    private String name;
    private UUID id;
    private Set<Course> courseSet;
    private Program program;
    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name, @JsonProperty("program") Program program) {
        this.courseSet = new HashSet<>();
        this.id = id;
        this.name = name;
        this.program = new Program(program.getId(), program);
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
    public void setCourseSet(Set<Course> newCourseSet) { this.courseSet = newCourseSet; }
    public Set<Course> getCourseSet() { return this.courseSet; }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
