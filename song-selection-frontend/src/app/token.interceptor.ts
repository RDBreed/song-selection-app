import {HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {catchError, throwError} from 'rxjs';
import {Router} from '@angular/router';
import {AuthService} from './auth.service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService); // Inject AuthService
  const router = inject(Router); // Inject Router

  // Definieer een lijst met URLs die je wilt uitsluiten van deze interceptor
  const excludedUrls = ['/api/protected'];

  // Controleer of de huidige URL overeenkomt met een uitgesloten URL
  const isExcluded = excludedUrls.some(url => req.url.includes(url));

  // Als de URL is uitgesloten, stuur het verzoek gewoon door zonder verdere afhandeling
  if (isExcluded) {
    return next(req);
  }
  return next(req)
    .pipe(  // Gebruik next(req) in plaats van next.handle(req)
      catchError((error: HttpErrorResponse) => {
        // Als een 401 Unauthorized fout optreedt, afhandelen
        if (error.status === 401) {
          authService.logout();  // Log de gebruiker uit
          router.navigate(['/login']); // Redirect naar login pagina
        }
        return throwError(() => error);  // Correcte manier om errors te throwen in rxjs v7+
      })
    );
};
