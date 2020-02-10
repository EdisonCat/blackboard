package com.edison.blackboard.model;

import java.util.UUID;

public class Professor extends Person {
    public Professor(UUID id, String name, Program program) {
        super(id, name, program);
    }
}
