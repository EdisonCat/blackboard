package com.edison.blackboard.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Student implements Person {
    private final UUID id;
    private String name;
    private Set<Course> courseList;

    public Student(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
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

    public void addCourse(Course course) {
        this.courseList.add(course);
    }

    public void removeCourse(Course course) {
        this.courseList.remove(course);
    }
}
