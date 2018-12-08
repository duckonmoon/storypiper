import { Component, OnInit } from '@angular/core';
import { WriteStoryService } from './write-story.service';

@Component({
  selector: 'app-write-story',
  templateUrl: './write-story.component.html',
  styleUrls: ['./write-story.component.css']
})
export class WriteStoryComponent implements OnInit {

  description: string;
  intro: string;
  title: string;
  success = false;
  fail = false;

  constructor(private storyService: WriteStoryService) { }

  ngOnInit() {
  }

  submit() {
    this.storyService.postStory(this.title, this.intro, this.description).subscribe(
      (i) => {
        this.success = true;
        this.fail = false;
      }, (e) => {
        console.log = e;
        this.success = false;
        this.fail = true;
      }
    )
  }

}
