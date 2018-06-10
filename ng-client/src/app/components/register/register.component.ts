import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: this.formBuilder.control(null, Validators.required),
      password: this.formBuilder.control(null, Validators.required),
    });
  }

  register() {
    if (!this.registerForm.valid || this.registerForm.pristine) {
      return;
    }

    this.authService.register$(this.registerForm.value).subscribe(
      () => {
        this.snackBar.open('Account was successfully created', 'Go to login page')
          .onAction().subscribe(() => this.router.navigateByUrl('/login'));
      },
    );
  }
}
