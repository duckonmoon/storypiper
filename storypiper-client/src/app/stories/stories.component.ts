import { Component, OnInit } from '@angular/core';
import { Test } from '../model/test';
import { StoryService } from './stories.service';

@Component({
  selector: 'app-stories',
  templateUrl: './stories.component.html',
  styleUrls: ['./stories.component.css']
})
export class StoriesComponent implements OnInit {

  test: Test;

  constructor(private service: StoryService) { }

  ngOnInit() {
    this.service.getStories().subscribe((test) => this.test = test);
  }

}
