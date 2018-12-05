import { Component, OnInit } from '@angular/core';
import StoryContainer from '../model/stories.container';
import { StoryService } from './stories.service';

@Component({
  selector: 'app-stories',
  templateUrl: './stories.component.html',
  styleUrls: ['./stories.component.css']
})
export class StoriesComponent implements OnInit {

  storyContainer: StoryContainer;

  constructor(private service: StoryService) { }

  ngOnInit() {
    this.service.getStories().subscribe((storyContainer) => this.storyContainer = storyContainer);
  }

}
