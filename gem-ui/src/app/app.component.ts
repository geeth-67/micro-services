import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  private readonly router = inject(Router);
  readonly auth = inject(AuthService);

  constructor() {
    this.auth.initAuth().catch((error) => {
      console.error('Error initializing auth service', error);
    });
  }

  /** Example of programmatic navigation from the app shell */
  navigateHome(): void {
    this.router.navigate(['/']);
  }
}
