import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    title: 'Home',
    loadComponent: () =>
      import('./home/home.component').then((m) => m.HomeComponent),
  },
  {
    path: 'gems',
    title: 'Gem Collection',
    loadComponent: () =>
      import('./gems/gems.component').then((m) => m.GemsComponent),
  },
  {
    path: 'login',
    title: 'Login',
    loadComponent: () =>
      import('./login/login.component').then((m) => m.LoginComponent),
  },
  {
    path: '**',
    redirectTo: '',
  },
];
