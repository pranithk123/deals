package com.lootdeals.lootdeals.deal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DealRepository extends JpaRepository<Deal, Long> {
    Optional<Deal> findBySlug(String slug);
    List<Deal> findTop20ByOrderByCreatedAtDesc();
    List<Deal> findByCategoryIgnoreCaseOrderByCreatedAtDesc(String category);
    List<Deal> findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String q);
}


