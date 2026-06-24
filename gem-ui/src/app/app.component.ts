import { Component , inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from "./login/login.component";
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'gem-ui';
  auth = inject(AuthService);

  constructor() {
    this.auth.initAuth().catch(error => {
      console.error('Error initializing auth service', error);
    });
  }
}
