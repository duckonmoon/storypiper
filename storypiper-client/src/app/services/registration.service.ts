import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class RegistrationService {

    constructor(private http: HttpClient) {
    }


    public register(email, nickname, password, confirmPassword): Observable<any> {
        const data = JSON.stringify({
            'Email': email,
            'Nickname': nickname,
            'Password': password,
            'ConfirmPassword': confirmPassword
        });

        let headers: HttpHeaders = new HttpHeaders();
        headers = headers.append( 'Content-Type', 'application/json' );

        return this.http.post('/api/Account/Register', data, {headers});
    }
}
