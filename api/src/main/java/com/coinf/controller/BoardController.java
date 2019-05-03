package com.coinf.controller;

import com.coinf.dto.Board;
import com.coinf.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/board/{gameId}")
    public Board getBoard(Long gameId) {
        // TODO: construct board for that game, using hexNodes and game specific data (units, buildings, resources, etc.)
        return new Board();
    }
}
