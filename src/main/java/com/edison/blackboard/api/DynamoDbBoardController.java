package com.edison.blackboard.api;

import com.edison.blackboard.model.Board;
import com.edison.blackboard.service.DynamoDbAnnouncementService;
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
    private final DynamoDbAnnouncementService dynamoDbAnnouncementService;

    @Autowired
    public DynamoDbBoardController(DynamoDbBoardService dynamoDbBoardService, DynamoDbAnnouncementService dynamoDbAnnouncementService) {
        this.dynamoDbBoardService = dynamoDbBoardService;
        this.dynamoDbAnnouncementService = dynamoDbAnnouncementService;
    }

    @PostMapping
    public boolean insertBoard(Board board) {
        dynamoDbBoardService.insertBoard(board);
        return true;
    }

    @GetMapping(path = "{boardId}")
    public ResponseEntity<Board> getBoardById(@PathVariable("boardId") UUID boardId) {
        Board board = dynamoDbBoardService.getBoardById(boardId);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @GetMapping
    public List<Board> getAllBoards() {
        return dynamoDbBoardService.getAllBoards();
    }

    @PutMapping
    public void updateBoard(@RequestBody Board board) {
        dynamoDbBoardService.updateBoard(board);
    }

    @DeleteMapping(path = "{boardId}")
    public void deleteBoardById(@PathVariable("boardId") UUID boardId) {
        dynamoDbBoardService.deleteBoard(getBoardById(boardId).getBody());
    }

}
