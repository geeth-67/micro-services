import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  private readonly router = inject(Router);

  /** Programmatic navigation via Router.navigate() */
  navigateToGems(): void {
    this.router.navigate(['/gems']);
  }

  navigateToLogin(): void {
    this.router.navigate(['/login']);
  }
}
