package com.edison.blackboard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "course")
@DynamoDBDocument
public class Course implements Serializable {
    private String name;
    private UUID id = UUID.randomUUID();
    private List<Student> studentList = new ArrayList<>();
    private Professor professor;
    private Student ta;
    private UUID boardId;
    private Board board = new Board();
    private String notificationTopic;

    public Course(Course course) {
            setName(course.getName());
            setId(course.getId());
            setStudentList(course.getStudentList());
            setTa(course.getTa());
            setProfessor(course.getProfessor());
    }
    public Course(Course course, boolean light) {
        if(light) {
            setName(course.getName());
            setId(course.getId());
            if(course.ta != null) setTa(new Student(course.getTa(), true));
            if(course.professor != null) setProfessor(new Professor(course.getProfessor(), true));
        }
    }

    public Course() { }

    @DynamoDBAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBHashKey(attributeName = "courseid")
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
    public Professor getProfessor() {
        return professor;
    }

    public Course setProfessor(Professor professor) {
        this.professor = professor;
        return this;
    }

    @DynamoDBAttribute
    public String getNotificationTopic() {
        return notificationTopic;
    }

    public void setNotificationTopic(String notificationTopic) {
        this.notificationTopic = notificationTopic;
    }

    @DynamoDBAttribute
    public Board getBoard() {
        return this.board;
    }

    public Course setBoard(Board board) {
        if(board == null) {
            this.board = null;
            setBoardId(null);
            return this;
        }
        this.board = new Board(board, true);
        setBoardId(this.board.getId());
        return this;
    }

    @DynamoDBAttribute
    public Student getTa() {
        return ta;
    }

    public Course setTa(Student ta) {
        this.ta = ta;
        return this;
    }

    public Course removeTa(Student ta) {
        if(this.ta.getId().equals(ta.getId())) setTa(new Student());
        else System.out.println("TA Not Found");
        return this;
    }

    @DynamoDBAttribute
    public UUID getBoardId() {
        return boardId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }

    public Course addStudent(Student student) {
        this.studentList.add(new Student(student, true));
        return this;
    }

    public Course removeStudent(Student student) {
        for(int i = 0; i < studentList.size(); i++) {
            if(studentList.get(i).getId().equals(student.getId())) {
                studentList.remove(i);
                return this;
            }
        }
        return null;
    }

    public Course removeProfessor(Professor professor) {
        if(professor == null) {
            System.out.println("Professor Not Found");
            return this;
        }
        if(this.professor.getId().equals(professor.getId())) setProfessor(new Professor());
        else System.out.println("Professor Not Found");
        return this;
    }

    public Course removeBoard() {
        setBoard(null);
        return this;
    }
}
