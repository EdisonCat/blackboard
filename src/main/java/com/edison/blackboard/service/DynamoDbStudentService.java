package com.edison.blackboard.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.edison.blackboard.model.Course;
import com.edison.blackboard.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DynamoDbStudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBMapper.class);

    private final DynamoDBMapper mapper;
    private final DynamoDbCourseService dynamoDbCourseService;
    private final DynamoDbProgramService dynamoDbProgramService;

    @Autowired
    public DynamoDbStudentService(DynamoDBMapper mapper,
                                  DynamoDbCourseService dynamoDbCourseService,
                                  DynamoDbProgramService dynamoDbProgramService) {
        this.mapper = mapper;
        this.dynamoDbCourseService = dynamoDbCourseService;
        this.dynamoDbProgramService = dynamoDbProgramService;
    }

    public void insertStudent(Student student) {
        mapper.save(student);
    }

    public Student getStudentById(UUID id) {
        return mapper.load(Student.class, id);
    }

    public void updateStudent(Student student) {
        try {
            mapper.save(student, buildDynamoDBSaveExpression(student));
        }
        catch (ConditionalCheckFailedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(Student.class, scanExpression);
    }


    public void deleteStudent(Student student) {
        mapper.delete(student);
    }

    public DynamoDBSaveExpression buildDynamoDBSaveExpression(Student student) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("studentid", new ExpectedAttributeValue(new AttributeValue(student.getId().toString())));
        saveExpression.setExpected(expectedAttributeValueMap);
        return saveExpression;
    }

    public List<Course> getAllCourses(UUID studentId) {
        return getStudentById(studentId).getCourseList();
    }

    public boolean addCourse(UUID studentId, UUID courseId) {
        updateStudent(getStudentById(studentId).addCourse(dynamoDbCourseService.getCourseById(courseId)));
        dynamoDbCourseService.updateCourse(dynamoDbCourseService.getCourseById(courseId)
                .addStudent(getStudentById(studentId)));
        return true;
    }

    public boolean removeCourse(UUID studentId, UUID courseId) {
        updateStudent(getStudentById(studentId).removeCourse(dynamoDbCourseService.getCourseById(courseId)));
        dynamoDbCourseService.updateCourse(dynamoDbCourseService.getCourseById(courseId)
                .removeStudent(getStudentById(studentId)));
        return true;
    }

    public boolean addProgram(UUID studentId, UUID programId) {
        updateStudent(getStudentById(studentId).setProgram(dynamoDbProgramService.getProgramById(programId).getName()));
        dynamoDbProgramService.updateProgram(dynamoDbProgramService.getProgramById(programId)
                .addStudent(getStudentById(studentId)));
        return true;
    }

    public boolean removeProgram(UUID studentId, UUID programId) {
        updateStudent(getStudentById(studentId).removeProgram(dynamoDbProgramService.getProgramById(programId)));
        dynamoDbProgramService.updateProgram(dynamoDbProgramService.getProgramById(programId)
                .removeStudent(getStudentById(studentId)));
        return true;
    }
}
