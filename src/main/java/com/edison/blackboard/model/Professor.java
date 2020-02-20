package com.edison.blackboard.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DynamoDBTable(tableName = "professor")
@DynamoDBDocument
public class Professor implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID id;
    private String name = "";
    private String program = "";
    private List<Course> courseList = new ArrayList<>();

    public Professor(Professor professor) {
        setId(professor.getId());
        setName(professor.getName());
        setProgram(professor.getProgram());
        setCourseList(professor.getCourseList());
    }

    public Professor(Professor professor, boolean light) {
        if(light) {
            setId(professor.getId());
            setName(professor.getName());
        }
    }

    public Professor() {}

    @DynamoDBHashKey(attributeName = "professorid")
    @DynamoDBAutoGeneratedKey
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute
    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @DynamoDBAttribute
    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Professor addCourse(Course course) {
        this.courseList.add(new Course(course, true));
        return this;
    }

    public Professor removeCourse(Course course) {
        for(int i = 0; i < courseList.size(); i++) {
            if(courseList.get(i).getId().equals(course.getId())) {
                courseList.remove(i);
                return this;
            }
        }
        return null;
    }

    public Professor addProgram(Program program) {
        setProgram(program.getName());
        return this;
    }

    public Professor removeProgram(Program program) {
        if(this.program.equals(program.getName())) this.program = "";
        return this;
    }
}
