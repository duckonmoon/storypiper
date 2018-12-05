package com.duckonmoon.storypiper.storypiper.controller;

import com.duckonmoon.storypiper.storypiper.model.Status;
import com.duckonmoon.storypiper.storypiper.model.Story;
import com.duckonmoon.storypiper.storypiper.model.StoryVote;
import com.duckonmoon.storypiper.storypiper.model.User;
import com.duckonmoon.storypiper.storypiper.payload.AddStoryRequest;
import com.duckonmoon.storypiper.storypiper.payload.ShortStory;
import com.duckonmoon.storypiper.storypiper.payload.StoriesResponse;
import com.duckonmoon.storypiper.storypiper.payload.SuccessObjectCreatedResponse;
import com.duckonmoon.storypiper.storypiper.repository.StoryRepository;
import com.duckonmoon.storypiper.storypiper.repository.UserRepository;
import com.duckonmoon.storypiper.storypiper.security.CurrentUser;
import com.duckonmoon.storypiper.storypiper.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/story")
public class StoryController {
    private final StoryRepository storyRepository;
    private final UserRepository userRepository;

    @Autowired
    public StoryController(StoryRepository storyRepository, UserRepository userRepository) {
        this.storyRepository = storyRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/all")
    public StoriesResponse getIntroStories() {
        StoriesResponse response = new StoriesResponse();

        List<Story> storiesFromDb = storyRepository.getApprovedStories();

        response.setStories(
                storiesFromDb.stream()
                        .map((story -> {
                            ShortStory shortStory = new ShortStory();
                            shortStory.setId(story.getId());
                            shortStory.setCreatedBy(story.getCreatedBy().getUsername());
                            shortStory.setLikes(story.getVotes().stream().filter(StoryVote::isVote).collect(Collectors.toList()).size());
                            shortStory.setDislikes(story.getVotes().size() - shortStory.getLikes());
                            shortStory.setTitle(story.getTitle());
                            shortStory.setIntro(story.getIntro());
                            return shortStory;
                        }))
                        .sorted((i, j) -> i.getLikes() - j.getLikes() >= 0 ? 1 : -1)
                        .collect(Collectors.toList())
        );

        return response;
    }


    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public SuccessObjectCreatedResponse insertStory(@CurrentUser UserPrincipal userPrincipal, @Valid @RequestBody AddStoryRequest storyRequest) {
        Story story = new Story();

        User user = userRepository.findByUsername(userPrincipal.getUsername()).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        story.setIntro(storyRequest.getIntro());
        story.setLastUpdatedBy(user);
        story.setStatus(Status.OK);
        story.setText(storyRequest.getText());
        story.setTitle(storyRequest.getTitle());
        story.setLastUpdatedAt(new Date());
        story.setCreatedAt(new Date());
        story.setCreatedBy(user);

        story = storyRepository.save(story);

        return new SuccessObjectCreatedResponse(story.getId(),"story");
    }

}
