package com.edison.blackboard.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.edison.blackboard.model.Announcement;
import com.edison.blackboard.model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DynamoDbBoardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Board.class);

    private final DynamoDBMapper mapper;
    private final DynamoDbAnnouncementService dynamoDbAnnouncementService;

    @Autowired
    public DynamoDbBoardService(DynamoDBMapper mapper, DynamoDbAnnouncementService dynamoDbAnnouncementService) {
        this.mapper = mapper;
        this.dynamoDbAnnouncementService = dynamoDbAnnouncementService;
    }

    public void insertBoard(Board board) {
        mapper.save(board);
    }

    public Board getBoardById(UUID id) {
        return mapper.load(Board.class, id);
    }

    public void updateBoard(Board board) {
        try {
            mapper.save(board, buildDynamoDBSaveExpression(board));
        }
        catch (ConditionalCheckFailedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public List<Board> getAllBoards() {
        return mapper.scan(Board.class, new DynamoDBScanExpression());
    }

    public List<Announcement> getAllAnnouncements(UUID boardId) {
        return getBoardById(boardId).getAnnouncementList();
    }

    public void deleteBoard(Board board) {
        mapper.delete(board);
    }

    public DynamoDBSaveExpression buildDynamoDBSaveExpression(Board board) {
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("boardid", new ExpectedAttributeValue(new AttributeValue(board.getId().toString())));
        saveExpression.setExpected(expectedAttributeValueMap);
        return saveExpression;
    }

    public void addAnnouncement(UUID boardId, UUID announcementId) {
        updateBoard(getBoardById(boardId).addAnnouncement(dynamoDbAnnouncementService.getAnnouncementById(announcementId)));
        dynamoDbAnnouncementService.updateAnnouncement(dynamoDbAnnouncementService.getAnnouncementById(announcementId)
                .setBoard(getBoardById(boardId)));
    }

    public void removeAnnouncement(UUID boardId, UUID announcementId) {
        updateBoard(getBoardById(boardId).removeAnnouncement(dynamoDbAnnouncementService.getAnnouncementById(announcementId)));
        dynamoDbAnnouncementService.updateAnnouncement(dynamoDbAnnouncementService.getAnnouncementById(announcementId)
                .deleteBoard(getBoardById(boardId)));
    }
}
