package com.edison.blackboard.model;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Professor implements Person {
    private String name;
    private final UUID id;
    private Set<Course> courseList;

    public Professor(UUID id, String name) {
        this.name = name;
        this.id = id;
        this.courseList = new HashSet<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addCourse(Course course) {
        this.courseList.add(course);
    }

    @Override
    public void removeCourse(Course course) {
        this.courseList.remove(course);
    }

    @Override
    public boolean isRegistered(Course course) {
        return this.courseList.contains(course);
    }

}
