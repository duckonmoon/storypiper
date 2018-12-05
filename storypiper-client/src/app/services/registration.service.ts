import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class RegistrationService {

    constructor(private http: HttpClient) {
    }


    public register(email, name, nickname, password): Observable<any> {
        const data = JSON.stringify({
            'email': email,
            'name': name,
            'username': nickname,
            'password': password
        });

        let headers: HttpHeaders = new HttpHeaders();
        headers = headers.append( 'Content-Type', 'application/json' );

        return this.http.post('/api/auth/signup', data, {headers});
    }
}
