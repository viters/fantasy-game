import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from 'src/app/components/login/login.component';
import { OnlyUserGuard } from './services/only-user.guard';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RegisterComponent } from './components/register/register.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { ElementsComponent } from './components/elements/elements.component';

const routes: Routes = [
  {
    path: '', canActivate: [OnlyUserGuard], component: DashboardComponent, children: [
      {path: 'elements', component: ElementsComponent},
      {path: 'categories', component: CategoriesComponent},
      {path: 'metadata', redirectTo: ''},
    ],
  },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
];

export const AppRouter = RouterModule.forRoot(routes);

