package com.edison.blackboard.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.edison.blackboard.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DynamoDbCourseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBMapper.class);

    private final DynamoDBMapper mapper;

    @Autowired
    public DynamoDbCourseService(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public void insertCourse(Course course) {
        mapper.save(course);
    }

    public Course getCourseById(UUID id) {
        return mapper.load(Course.class, id);
    }

    public void updateCourse(Course course) {
        try {
            mapper.save(course, buildDynamoDBSaveExpression(course));
        }
        catch (ConditionalCheckFailedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public List<Course> getAllCourses() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(Course.class, scanExpression);
    }


    public void deleteCourse(Course course) {
        mapper.delete(course);
    }

    public DynamoDBSaveExpression buildDynamoDBSaveExpression(Course course) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("courseid", new ExpectedAttributeValue(new AttributeValue(course.getId().toString())));
        saveExpression.setExpected(expectedAttributeValueMap);
        return saveExpression;
    }
}
