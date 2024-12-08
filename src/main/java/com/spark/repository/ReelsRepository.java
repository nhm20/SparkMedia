package com.spark.repository;

import com.spark.model.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels, Long> {

    List<Reels> findByUserId(Long userId);
}
