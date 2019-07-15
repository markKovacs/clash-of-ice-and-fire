package com.coinf.repository;

import com.coinf.entity.blueprint.PlayerMatLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerMatLayoutRepository extends JpaRepository<PlayerMatLayout, Long> {
}
