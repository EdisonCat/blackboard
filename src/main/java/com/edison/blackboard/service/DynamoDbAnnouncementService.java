package com.edison.blackboard.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.edison.blackboard.model.Announcement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DynamoDbAnnouncementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBMapper.class);
    private final DynamoDbBoardService dynamoDbBoardService;
    private final DynamoDBMapper mapper;

    @Autowired
    public DynamoDbAnnouncementService(DynamoDBMapper mapper, @Lazy DynamoDbBoardService dynamoDbBoardService) {
        this.mapper = mapper;
        this.dynamoDbBoardService = dynamoDbBoardService;
    }

    public void insertAnnouncement(Announcement announcement) {
        mapper.save(announcement);
    }

    public Announcement getAnnouncementById(UUID id) {
        return mapper.load(Announcement.class, id);
    }

    public void updateAnnouncement(Announcement announcement) {
        try {
            mapper.save(announcement, buildDynamoDbSaveExpression(announcement));
        }
        catch (ConditionalCheckFailedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public List<Announcement> getAllAnnouncements() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(Announcement.class, scanExpression);
    }

    public void setBoard(UUID announcementId, UUID boardId) {
        updateAnnouncement(getAnnouncementById(announcementId).setBoard(dynamoDbBoardService.getBoardById(boardId)));
        dynamoDbBoardService.updateBoard(dynamoDbBoardService.getBoardById(boardId)
                .addAnnouncement(getAnnouncementById(announcementId)));
    }

    public void deleteAnnouncement(Announcement announcement) {
        mapper.delete(announcement);
    }

    public DynamoDBSaveExpression buildDynamoDbSaveExpression(Announcement announcement) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("announcementid", new ExpectedAttributeValue(new AttributeValue(announcement.getId().toString())));
        saveExpression.setExpected(expectedAttributeValueMap);
        return saveExpression;
    }

}
