package com.coinf.repository;

import com.coinf.entity.blueprint.FactionMatLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionMatLayoutRepository extends JpaRepository<FactionMatLayout, Long> {
}
