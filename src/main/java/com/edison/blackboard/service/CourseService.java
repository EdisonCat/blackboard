package com.edison.blackboard.service;

import com.edison.blackboard.dao.CourseDao;
import com.edison.blackboard.model.Course;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {
    private final CourseDao courseDao;

    public CourseService(@Qualifier("CourseDao") CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public boolean addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    public Course getCourseByIndex(int index) {
        return courseDao.getCourseByIndex(index);
    }

    public List<Course> getAllCourses() {
        return courseDao.selectAllCourses();
    }

    public Optional<Course> getCourseById(UUID id) { return courseDao.selectCourseById(id); }

    public boolean deleteCourseById(UUID id) {
        return courseDao.deleteCourseById(id);
    }

    public boolean updateCourseById(UUID id, Course course) {
        return courseDao.updateCourseById(id, course);
    }
}
