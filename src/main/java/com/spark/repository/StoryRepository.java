package com.spark.repository;

import com.spark.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    public List<Story>findByUserId(Long userId);
}
