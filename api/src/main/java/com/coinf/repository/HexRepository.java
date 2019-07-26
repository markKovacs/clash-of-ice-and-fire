package com.coinf.repository;

import com.coinf.entity.instance.Game;
import com.coinf.entity.instance.Hex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HexRepository extends JpaRepository<Hex, Long> {

    Hex findByGameAndHexNodeId(Game game, Long id);

    @Query("SELECT h FROM Hex h WHERE h.game = :game AND h.hexNodeId IN :ids")
    List<Hex> batchGetByGameAndHexNodeId(@Param("game") Game game,@Param("ids") List<Long> ids);

}
