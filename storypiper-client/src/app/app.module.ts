import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { StoriesComponent } from './stories/stories.component';
import { StoryComponent } from './stories/story/story.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthGuard } from './__guards/auth.guard';
import { RegistrationService } from './services/registration.service';
import { LoginService } from './services/login.service';
import { JwtInterceptor } from './__helper/jwt.interceptor';
import { ErrorInterceptor } from './__helper/error.interceptor';
import { LoginGuard } from './__guards/login.guard';
import { StoryService } from './stories/stories.service';
import { VoteService } from './services/vote.service';
import { ProfileComponent } from './profile/profile.component';
import { WriteStoryComponent } from './write-story/write-story.component';
import { WriteStoryService } from './write-story/write-story.service';
import { StoryDetailsComponent } from './story-details/story-details.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    StoriesComponent,
    StoryComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    WriteStoryComponent,
    StoryDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    AuthGuard,
    LoginGuard,
    RegistrationService,
    LoginService,
    StoryService,
    VoteService,
    WriteStoryService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
