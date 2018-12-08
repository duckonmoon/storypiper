import {Subscription} from 'rxjs';
import {AuthGuard} from '../__guards/auth.guard';
import {StoryService} from '../stories/stories.service';
import {Component, OnInit, OnDestroy} from '@angular/core';
import FullStory from '../model/full-story';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-story-details',
  templateUrl: './story-details.component.html',
  styleUrls: ['./story-details.component.css']
})
export class StoryDetailsComponent implements OnInit, OnDestroy {

  private story: FullStory;
  private error = false;
  private isEdit = false;
  private routeSub: any;
  isActiveSub: Subscription;

  private isActive: boolean;

  constructor(private storyService: StoryService, private route: ActivatedRoute, private auth: AuthGuard) {
  }

  ngOnInit() {
    this.routeSub = this.route.params.subscribe((params) => {
      this.storyService.getStory(params['storyId']).subscribe((response) => {
        this.story = response;
      });
    });

    this.isActiveSub = this.auth.eventTosubscribe().subscribe((next) => {
      this.isActive = next;
    });
    this.isActive = this.auth.isActive();
  }

  ngOnDestroy(): void {
    this.routeSub.unsubscribe();
    this.isActiveSub.unsubscribe();
  }

  edit(): void {
    this.isEdit = true;
  }

  cancel(): void {
    this.isEdit = false;
    this.ngOnDestroy();
    this.ngOnInit();
  }
}
