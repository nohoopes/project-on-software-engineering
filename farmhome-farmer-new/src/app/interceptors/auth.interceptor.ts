import {Injectable} from '@angular/core';
import { environment } from 'src/environments/environment';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse, HttpClient
} from '@angular/common/http';
import {catchError, Observable, switchMap, throwError} from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  static accessToken = '';
  refresh = false;
  baseApiUrl: string = environment.baseApiUrl;

  constructor(private http: HttpClient) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const req = request.clone({
      setHeaders: {
        Authorization: `Bearer ${AuthInterceptor.accessToken}`
      }
    });

    return next.handle(req).pipe(catchError((err: HttpErrorResponse) => {
      if (err.status === 401 && !this.refresh) {
        this.refresh = true;

        return this.http.post(this.baseApiUrl + '/signin', {}, {withCredentials: true}).pipe(
          switchMap((res: any) => {
            AuthInterceptor.accessToken = res.accessToken;

            return next.handle(request.clone({
              setHeaders: {
                Authorization: `Bearer ${AuthInterceptor.accessToken}`
              }
            }));
          })
        );
      }
      this.refresh = false;
      return throwError(() => err);
    }));
  }
}