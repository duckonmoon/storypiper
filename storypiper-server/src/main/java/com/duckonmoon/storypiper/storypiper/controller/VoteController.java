package com.duckonmoon.storypiper.storypiper.controller;

import com.duckonmoon.storypiper.storypiper.model.Story;
import com.duckonmoon.storypiper.storypiper.model.StoryVote;
import com.duckonmoon.storypiper.storypiper.model.User;
import com.duckonmoon.storypiper.storypiper.payload.VoteResponse;
import com.duckonmoon.storypiper.storypiper.repository.StoryRepository;
import com.duckonmoon.storypiper.storypiper.repository.StoryVoteRepository;
import com.duckonmoon.storypiper.storypiper.repository.UserRepository;
import com.duckonmoon.storypiper.storypiper.security.CurrentUser;
import com.duckonmoon.storypiper.storypiper.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vote")
public class VoteController {
    private final UserRepository userRepository;

    private final StoryVoteRepository voteRepository;

    private final StoryRepository storyRepository;

    @Autowired
    public VoteController(UserRepository userRepository, StoryVoteRepository voteRepository, StoryRepository storyRepository) {
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
        this.storyRepository = storyRepository;
    }

    @PostMapping
    public VoteResponse getIntroStories(
            @RequestParam(value = "up") Boolean isUp,
            @RequestParam(value = "storyId") Long id,
            @CurrentUser UserPrincipal userPrincipal) {
        if (isUp == null) {
            throw new RuntimeException("Votes must be + or - not undefined");
        }

        if (id == null) {
            throw new RuntimeException("Id must be specified");
        }

        Story story = storyRepository.getOne(id);

        if (story == null) {
            throw new RuntimeException("Story not found");
        }

        User user = userRepository.findByUsername(userPrincipal.getUsername()).orElse(null);

        if (user == null) {
            throw new RuntimeException("Smth went terribly wrong");
        }

        StoryVote storyVote = voteRepository.getByStoryIsAndUserIs(story, user);

        if (storyVote != null) {
            if (isUp == storyVote.isVote()) {
                voteRepository.delete(storyVote);
                return new VoteResponse(storyVote.getId(),
                        storyVote.isVote(),
                        false,
                        true);
            } else {
                storyVote.setVote(!storyVote.isVote());
                storyVote = voteRepository.save(storyVote);
                return new VoteResponse(storyVote.getId(),
                        storyVote.isVote(),
                        true,
                        false);
            }
        } else {
            storyVote = new StoryVote(user, isUp, story);
            storyVote = voteRepository.save(storyVote);
            return new VoteResponse(storyVote.getId(),
                    storyVote.isVote(),
                    false,
                    false);
        }
    }
}
