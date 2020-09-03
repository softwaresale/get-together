import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginBtnComponent } from './login-btn/login-btn.component';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  declarations: [LoginBtnComponent],
  imports: [
    CommonModule,
    MatButtonModule,
  ],
  exports: [LoginBtnComponent],
})
export class AuthModule { }
