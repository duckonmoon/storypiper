import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StoriesComponent } from './stories/stories.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CanActivate } from '@angular/router/src/utils/preactivation';
import { LoginGuard } from './__guards/login.guard';
import { ProfileComponent } from './profile/profile.component';
import { AuthGuard } from './__guards/auth.guard';
import { WriteStoryComponent } from './write-story/write-story.component';
import { StoryDetailsComponent } from './story-details/story-details.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/stories',
    pathMatch: 'full'
  },
  {
    path: 'stories',
    component: StoriesComponent
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [LoginGuard]
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'writeStory',
    component: WriteStoryComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'story/:storyId',
    component: StoryDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
