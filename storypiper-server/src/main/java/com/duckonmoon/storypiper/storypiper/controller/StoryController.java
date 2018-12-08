package com.duckonmoon.storypiper.storypiper.controller;

import com.duckonmoon.storypiper.storypiper.model.Status;
import com.duckonmoon.storypiper.storypiper.model.Story;
import com.duckonmoon.storypiper.storypiper.model.StoryVersion;
import com.duckonmoon.storypiper.storypiper.model.StoryVote;
import com.duckonmoon.storypiper.storypiper.model.User;
import com.duckonmoon.storypiper.storypiper.payload.AddStoryRequest;
import com.duckonmoon.storypiper.storypiper.payload.FullStoryComment;
import com.duckonmoon.storypiper.storypiper.payload.FullStoryResponse;
import com.duckonmoon.storypiper.storypiper.payload.FullStoryVersion;
import com.duckonmoon.storypiper.storypiper.payload.ShortStory;
import com.duckonmoon.storypiper.storypiper.payload.StoriesResponse;
import com.duckonmoon.storypiper.storypiper.payload.SuccessObjectCreatedResponse;
import com.duckonmoon.storypiper.storypiper.repository.StoryRepository;
import com.duckonmoon.storypiper.storypiper.repository.StoryVersionRepository;
import com.duckonmoon.storypiper.storypiper.repository.UserRepository;
import com.duckonmoon.storypiper.storypiper.security.CurrentUser;
import com.duckonmoon.storypiper.storypiper.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/story")
public class StoryController {
    private final StoryRepository storyRepository;
    private final UserRepository userRepository;
    private final StoryVersionRepository storyVersionRepository;

    @Autowired
    public StoryController(StoryRepository storyRepository, UserRepository userRepository, StoryVersionRepository storyVersionRepository) {
        this.storyRepository = storyRepository;
        this.userRepository = userRepository;
        this.storyVersionRepository = storyVersionRepository;
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

    @PostMapping("/{id}/update")
    public Long updateStory(@PathVariable("id") long id,@RequestBody AddStoryRequest storyRequest) {
        Story story = storyRepository.getOne(id);

        if (story == null) {
            throw new RuntimeException("Story doesn't exists");
        }

        story.setTitle(storyRequest.getTitle());
        story.setIntro(storyRequest.getIntro());
        story.setText(storyRequest.getText());

        story = storyRepository.save(story);

        return story.getId();
    }

    @GetMapping("/{id}")
    public FullStoryResponse getStory(@PathVariable("id") long id) {
        Story story = storyRepository.getOne(id);

        if (story == null) {
            throw new RuntimeException("Story doesn't exists");
        }
        String pattern = "EEEEE dd MMMMM yyyy HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat(pattern, Locale.getDefault());

        FullStoryResponse storyResponse = new FullStoryResponse(
            story.getId(),
            story.getTitle(),
            story.getIntro(),
            story.getText(),
            story.getStatus(),
            simpleDateFormat.format(story.getLastUpdatedAt()),
            Stream.concat(story.getUsers().stream(),Stream.of(story.getCreatedBy())).map(User::getUsername).collect(Collectors.toSet()),
            story.getVotes().stream().filter(StoryVote::isVote).collect(Collectors.toList()).size(),
            story.getVotes().stream().filter(v -> !v.isVote()).collect(Collectors.toList()).size(),
            story.getStoryVersions().stream().map((storyVersion ->
                new FullStoryVersion(
                    storyVersion.getId(),
                    storyVersion.getStatus(),
                    simpleDateFormat.format(storyVersion.getCreatedAt()),
                    storyVersion.getCreatedBy().getUsername()
            ))).collect(Collectors.toList()),
            story.getComments().stream().map((comment ->
                new FullStoryComment(
                    comment.getText(),
                    comment.getStatus()
                )))
                .collect(Collectors.toList())
        );

        return storyResponse;
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

        StoryVersion storyVersion = new StoryVersion();
        storyVersion.setCreatedBy(user);
        storyVersion.setStatus(Status.OK);
        storyVersion.setCreatedAt(new Date());
        storyVersion.setIntro(storyRequest.getIntro());
        storyVersion.setText(storyRequest.getText());
        storyVersion.setTitle(storyRequest.getTitle());
        storyVersion.setStory(story);

        story.setStoryVersions(Collections.singletonList(storyVersion));


        story = storyRepository.save(story);
        storyVersionRepository.save(storyVersion);

        return new SuccessObjectCreatedResponse(story.getId(),"story");
    }

}
