package com.edison.blackboard.model;

import java.util.UUID;

public interface Person {
    String getName();
    UUID getId();
    void setName(String name);
    void addCourse(Course course);
    void removeCourse(Course course);
    boolean isRegistered(Course course);
}
