import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { LoginService } from '../services/login.service';
import { AuthGuard } from '../__guards/auth.guard';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  isActiveSub: Subscription;
  userName = '';
  isActive: boolean;

  constructor(private auth: AuthGuard, private service: LoginService) {
  }

  ngOnInit() {
    this.isActiveSub = this.auth.eventTosubscribe().subscribe((next) => {
      this.isActive = next;
      this.userName = this.auth.getUserName();
    });
    this.isActive = this.auth.isActive();
    this.userName = this.auth.getUserName();
  }

  ngOnDestroy() {
    this.isActiveSub.unsubscribe();
  }

  logOut() {
    this.service.logout();
  }

}
