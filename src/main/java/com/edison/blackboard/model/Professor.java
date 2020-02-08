package com.edison.blackboard.model;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Professor implements Person {
    private String name;
    private final UUID id;
    private Set<Course> courseList;

    public Professor(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.courseList = new HashSet<>();
    }
    public Professor() {
        this.id = UUID.randomUUID();
        this.courseList = new HashSet<>();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
