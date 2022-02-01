import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { User } from "src/app/models/user";
import { AuthService } from "src/app/services/auth.service";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
})
export class RegisterComponent implements OnInit {
  user: User = new User();

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {}

  registerUser() {
    this.authService.signUp(this.user).subscribe(
      (data) => {
        this.router.navigate(["/"]);
        console.log(`User registration data submitted`);
      },
      (error) => console.log(error)
    );
    this.user = new User();
  }
}
