package com.edison.blackboard.api;


import com.edison.blackboard.model.Course;
import com.edison.blackboard.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/blackboard/course")
@RestController
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllPrograms() {
        return courseService.getAllCourses();
    }

    @GetMapping(path = "{id}")
    public Course getCourseById(@PathVariable("id") UUID id) {
        return courseService.getCourseById(id).orElse(null);
    }

    @PostMapping
    public void addCourse(@Valid @NotNull @RequestBody Course course) {
        courseService.addCourse(course);
    }

    @PutMapping(path = "{id}")
    public boolean updateCourseById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Course course) {
        return courseService.updateCourseById(id, course);
    }

    @DeleteMapping(path = "{id}")
    public boolean deleteCourseById(@PathVariable("id") UUID id) {
        return courseService.deleteCourseById(id);
    }

}
