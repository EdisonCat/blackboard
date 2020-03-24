package com.edison.blackboard.api;

import com.edison.blackboard.model.Announcement;
import com.edison.blackboard.model.Board;
import com.edison.blackboard.service.DynamoDbBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/blackboard/dynamodb/board")
@RestController
public class DynamoDbBoardController {
    private final DynamoDbBoardService dynamoDbBoardService;

    @Autowired
    public DynamoDbBoardController(DynamoDbBoardService dynamoDbBoardService) {
        this.dynamoDbBoardService = dynamoDbBoardService;
    }

    @PostMapping
    public boolean insertBoard(@RequestBody Board board) {
        dynamoDbBoardService.insertBoard(board);
        return true;
    }

    @GetMapping(path = "{boardId}")
    public ResponseEntity<Board> getBoardById(@PathVariable("boardId") UUID boardId) {
        Board board = dynamoDbBoardService.getBoardById(boardId);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @GetMapping(path = "{boardId}/announcement")
    public List<Announcement> getAllAnnouncement(@PathVariable("boardId") UUID boardId) {
        return dynamoDbBoardService.getAllAnnouncements(boardId);
    }

    @GetMapping
    public List<Board> getAllBoards() {
        return dynamoDbBoardService.getAllBoards();
    }

    @PutMapping
    public void updateBoard(@RequestBody Board board) {
        dynamoDbBoardService.updateBoard(board);
    }

    @PutMapping("{boardId}/announcement/{announcementId}")
    public void addAnnouncement(@PathVariable("boardId") UUID boardId, @PathVariable("announcementId") UUID announcementId) {
        dynamoDbBoardService.addAnnouncement(boardId, announcementId);
    }

    @DeleteMapping(path = "{boardId}")
    public void deleteBoardById(@PathVariable("boardId") UUID boardId) {
        dynamoDbBoardService.deleteBoard(getBoardById(boardId).getBody());
    }

    @DeleteMapping(path = "{boardId}/announcement/{announcementId}")
    public void removeAnnouncement(@PathVariable("boardId") UUID boardId, @PathVariable("announcementId") UUID announcementId) {
        dynamoDbBoardService.removeAnnouncement(boardId, announcementId);
    }

    @DeleteMapping(path = "{boardId}/course")
    public void removeCourse(@PathVariable("boardId") UUID boardId) {
        dynamoDbBoardService.removeCourse(boardId);
    }

}
