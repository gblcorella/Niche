import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { LoginData } from "src/app/models/login-data";
import { AuthService } from "src/app/services/auth.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  loginData: LoginData = new LoginData();

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {}

  login() {
    this.authService.login(this.loginData);
    this.loginData = new LoginData();
  }
}
