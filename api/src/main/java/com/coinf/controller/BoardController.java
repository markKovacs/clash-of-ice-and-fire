package com.coinf.controller;

import com.coinf.dto.BoardDto;
import com.coinf.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    // TODO: Create GameController that returns a GameDto, which is the current state of whole game,
    //  containing the whole board, untis and other game info
    //  Could be separated into several services... use builder pattern for dto object constructions.

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/board/{gameId}")
    public BoardDto getBoard(Long gameId) {
        // TODO: construct board for that game, using hexNodes and game specific data (units, buildings, resources, etc.)
        return boardService.getBoard(gameId);
    }
}
