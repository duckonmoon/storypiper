import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from '../services/registration.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  private email = '';
  private nickname = '';
  private password = '';
  private confirmPassword = '';
  private hasError: boolean;
  private hasSuccess: boolean;

  constructor(private service: RegistrationService, private router: Router) {

  }

  ngOnInit() {

  }

  onClick() {
    if (!this.validate()) {
      return;
    }

    console.log(this.email + ' ' + this.password + ' ' + this.nickname + ' ' + this.confirmPassword);
    this.service.register(this.email, this.nickname, this.password, this.confirmPassword).subscribe(
      (data) => {
        this.hasError = false;
        this.hasSuccess = true;
      }, // success path
      error => this.hasError = true
    );
  }

  validate(): boolean {
    if (!this.validateEmail() || this.nickname === '' || !(this.confirmPassword === this.password) || this.password.length < 6) {
      this.hasError = true;
      this.hasSuccess = false;
      return false;
    }
    return true;
  }

  validateEmail = () => {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(this.email.toLowerCase());
  }

}
