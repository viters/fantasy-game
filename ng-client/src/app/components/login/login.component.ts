import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: this.formBuilder.control(null, Validators.required),
      password: this.formBuilder.control(null, Validators.required)
    });
  }

  login() {
    if (!this.loginForm.valid || this.loginForm.pristine) {
      return;
    }

    this.authService.login(this.loginForm.value);
  }
}
