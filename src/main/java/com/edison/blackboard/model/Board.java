package com.edison.blackboard.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DynamoDBTable(tableName = "board")
@DynamoDBDocument
public class Board {
    private UUID id;
    private UUID courseId;
    private List<Announcement> announcementList = new ArrayList<>();
    private String courseName = "";

    public Board(Board board) {
        setId(board.getId());
        setCourseId(board.getCourseId());
        setId(board.getId());
        setCourseName(board.getCourseName());
    }

    public Board(Board board, boolean light) {
        if(light) {
            if(board != null && board.getCourseId() != null) setCourseId(board.getCourseId());
            if(board != null && board.getId() != null) setId(board.getId());
        }
    }

    public Board(Course course) {
        setCourseId(course.getId());
        setCourseName(course.getName());
    }

    public Board() {
    }

    @DynamoDBHashKey(attributeName = "boardid")
    @DynamoDBAutoGeneratedKey
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String className) {
        this.courseName = className;
    }


    @DynamoDBAttribute
    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    @DynamoDBAttribute
    public List<Announcement> getAnnouncementList() {
        return this.announcementList;
    }

    public Board addAnnouncement(Announcement announcement) {
        this.announcementList.add(new Announcement(announcement, true));
        return this;
    }

    public void setAnnouncementList(List<Announcement> announcementList) {
        this.announcementList = new ArrayList<>(announcementList);
    }

    public Board removeAnnouncement(Announcement announcement) {
        if(announcement == null) {
            System.out.println("Announcement Not Found");
            return this;
        }
        for(int i = 0; i < announcementList.size(); i++) {
            if(announcementList.get(i).getId().equals(announcement.getId())) {
                announcementList.remove(i);
                return this;
            }
        }
        System.out.println("Announcement Not Found");
        return this;
    }

    public Board setCourse(Course course) {
        if(course == null) {
            System.out.println("Course can NOT be NULL");
            return this;
        }
        setCourseName(course.getName());
        setCourseId(course.getId());
        return this;
    }
}
