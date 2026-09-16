import { Routes } from '@angular/router';
import { APP_ROUTES } from './utils/constants/routes.constants';

export const routes: Routes = [

  {
    path: APP_ROUTES.INIT,
    redirectTo: APP_ROUTES.LOGIN,
    pathMatch: 'full'
  },
  {
    path: APP_ROUTES.LOGIN,
    loadComponent: () =>
      import('./pages/login/login').then(m => m.Login),
  },
  {
    path: APP_ROUTES.HOME,
    loadComponent: () =>
      import('./pages/home/home').then(m => m.Home),
  }
];
