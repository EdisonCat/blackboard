package com.edison.blackboard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "program")
public class Program implements Serializable {
    private String name = "";
    private UUID id;
    private List<Student> studentList = new ArrayList<>();
    private List<Professor> professorList = new ArrayList<>();
    private List<Course> courseList = new ArrayList<>();

    public Program(Program program) {
        setName(program.getName());
        setId(program.getId());
        setCourseList(program.getCourseList());
        setProfessorList(program.getProfessorList());
        setStudentList(program.getStudentList());
    }

    public Program() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBHashKey(attributeName = "programid")
    @DynamoDBAutoGeneratedKey
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @DynamoDBAttribute
    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    @DynamoDBAttribute
    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Program addStudent(Student student) {
        this.studentList.add(student);
        return this;
    }

    public Program removeStudent(Student student) {
        for(int i = 0; i < studentList.size(); i++) {
            if(studentList.get(i).getId().equals(student.getId())) {
                studentList.remove(i);
                return this;
            }
        }
        return null;
    }
}
