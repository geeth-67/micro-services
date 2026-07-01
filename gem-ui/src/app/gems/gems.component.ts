import { Component, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../service/auth.service';

interface Gem {
  id: number;
  name: string;
  type: string;
  caratWeight: number;
  pricePerCarat: number;
}

@Component({
  selector: 'app-gems',
  standalone: true,
  imports: [],
  templateUrl: './gems.component.html',
  styleUrl: './gems.component.scss'
})
export class GemsComponent {
   auth = inject(AuthService);
   private http = inject(HttpClient);

   gems = signal<Gem[]>([]);
}