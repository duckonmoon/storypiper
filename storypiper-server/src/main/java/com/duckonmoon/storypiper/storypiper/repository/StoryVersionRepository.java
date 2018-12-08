package com.duckonmoon.storypiper.storypiper.repository;

import com.duckonmoon.storypiper.storypiper.model.StoryVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryVersionRepository extends JpaRepository<StoryVersion,Long> {
}
