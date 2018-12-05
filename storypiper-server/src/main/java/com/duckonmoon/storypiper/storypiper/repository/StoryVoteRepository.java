package com.duckonmoon.storypiper.storypiper.repository;

import com.duckonmoon.storypiper.storypiper.model.Story;
import com.duckonmoon.storypiper.storypiper.model.StoryVote;
import com.duckonmoon.storypiper.storypiper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryVoteRepository extends JpaRepository<StoryVote, Long> {
    StoryVote getByStoryIsAndUserIs(Story story, User user);
}
