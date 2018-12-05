package com.duckonmoon.storypiper.storypiper.repository;

import com.duckonmoon.storypiper.storypiper.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    @Query("from Story where status = com.duckonmoon.storypiper.storypiper.model.Status.OK")
    List<Story> getApprovedStories();
}
