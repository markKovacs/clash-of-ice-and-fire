package com.coinf.repository;

import com.coinf.entity.blueprint.HexNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HexNodeRepository extends JpaRepository<HexNode, Long> {
}
