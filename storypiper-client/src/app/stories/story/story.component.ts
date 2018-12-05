import { Component, OnInit, Input } from '@angular/core';
import ShortStory from 'src/app/model/short.story';
import { VoteService } from 'src/app/services/vote.service';
import { Vote } from 'src/app/model/vote';

@Component({
  selector: 'app-story',
  templateUrl: './story.component.html',
  styleUrls: ['./story.component.css']
})
export class StoryComponent implements OnInit {

  @Input()
  shortStory: ShortStory;

  constructor(private voteService: VoteService) { }

  ngOnInit() {
  }

  onLikeClick() {
    this.voteService.vote(this.shortStory.id, true).subscribe((data) => {
      const vote: Vote = data;
      if (vote.changed) {
        this.shortStory.likes++;
        this.shortStory.dislikes--;
      } else if (vote.deleted) {
        this.shortStory.likes--;
      } else {
        this.shortStory.likes++;
      }
    });
  }

  onDislikeClick() {
    this.voteService.vote(this.shortStory.id, false).subscribe((data) => {
      const vote: Vote = data;
      if (vote.changed) {
        this.shortStory.likes--;
        this.shortStory.dislikes++;
      } else if (vote.deleted) {
        this.shortStory.dislikes--;
      } else {
        this.shortStory.dislikes++;
      }
    });
  }

}
