package com.edison.blackboard.model;

import java.util.UUID;

public class Student extends Person {
    public Student(UUID id, String name, Program program) {
        super(id, name, program);
    }
}
