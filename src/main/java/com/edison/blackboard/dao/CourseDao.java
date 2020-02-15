package com.edison.blackboard.dao;

import com.edison.blackboard.model.Course;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("CourseDao")
public class CourseDao {
    private static List<Course> courseList = new ArrayList<>();
    static {

    }
    public boolean addCourse(Course course) {
        UUID id = UUID.randomUUID();
        return insertCourse(id, course);
    }

    public Course getCourseByIndex(int index) {
        return courseList.get(index);
    }

    public boolean insertCourse(UUID id, Course course) {
//        courseList.add(new Course(id, course));
        return true;
    }

    public List<Course> selectAllCourses() {
        return courseList;
    }

    public boolean deleteCourseById(UUID id) {
        Optional<Course> toBeDeleted = selectCourseById(id);
        if(toBeDeleted.isEmpty()) return false;
        courseList.remove(toBeDeleted.get());
        return true;
    }

    public boolean updateCourseById(UUID id, Course course) {
//        Course newCourse = new Course(id, course.getName());
//        return selectCourseById(id).map(course1 -> {
//            int index = courseList.indexOf(course1);
//            if(index >= 0) {
//                courseList.set(0, newCourse);
//                return true;
//            }
//            return false;
//        }).orElse(false);
        return true;
    }

    public Optional<Course> selectCourseById(UUID id) {
        return courseList.stream().filter(course -> course.getId().equals(id)).findFirst();
    }
}
