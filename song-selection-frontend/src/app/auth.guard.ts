import {inject} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivateChildFn, CanActivateFn, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from './auth.service';
import {of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';

export const canActivate: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const authService = inject(AuthService);
  const router = inject(Router);
// return true;
  return authService.checkTokenValidity().pipe(
    map(() => {
      return true;
    }),
    catchError(() => {
      router.navigate(['/login']); // Redirect to login on error
      return of(false); // Block the route
    })
  );
};

export const canActivateChild: CanActivateChildFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => canActivate(route, state);
