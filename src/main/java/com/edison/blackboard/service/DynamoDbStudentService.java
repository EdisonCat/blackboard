package com.edison.blackboard.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.edison.blackboard.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DynamoDbStudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBMapper.class);

    @Autowired
    private DynamoDBMapper mapper;

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

    public void deleteStudent(Student student) {
        mapper.delete(student);
    }

    public DynamoDBSaveExpression buildDynamoDBSaveExpression(Student student) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("studentId", new ExpectedAttributeValue(new AttributeValue(student.getId().toString())))
                .withComparisonOperator(ComparisonOperator.EQ);
        saveExpression.setExpected(expectedAttributeValueMap);
        return saveExpression;
    }
}
