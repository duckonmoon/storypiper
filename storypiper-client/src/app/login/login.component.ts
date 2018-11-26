import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private nickname: string;
  private password: string;

  constructor(private service: LoginService) { }

  ngOnInit() {
  }

  onClick() {
    this.service.login(this.nickname, this.password);
  }

}

