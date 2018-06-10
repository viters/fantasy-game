import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from 'src/app/components/login/login.component';
import { OnlyUserGuard } from './services/only-user.guard';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const routes: Routes = [
  {path: '', canActivate: [OnlyUserGuard], component: DashboardComponent},
  {path: 'login', component: LoginComponent},
];

export const AppRouter = RouterModule.forRoot(routes);

