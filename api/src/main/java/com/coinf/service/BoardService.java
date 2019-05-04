package com.coinf.service;

import com.coinf.dto.BoardDto;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    // TODO: maybe rather create GameService which is responsible for returning initial/current state of whole game,
    //  or just break down into services (board-hexes, units, etc.)

    public BoardDto getBoard(Long gameId) {

        return null;
    }

}
