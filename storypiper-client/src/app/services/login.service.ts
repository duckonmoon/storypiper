import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthGuard } from '../__guards/auth.guard';

@Injectable()
export class LoginService {

    constructor(private http: HttpClient, private auth: AuthGuard) {
    }


    public login(userName, password) {
        let headers: HttpHeaders = new HttpHeaders();
        headers = headers.append('Content-Type', 'application/json');

        this.http
            .post('/api/auth/signin',
                JSON.stringify(
                    {
                        'usernameOrEmail': userName,
                        'password': password
                    }
                ),
                { headers }
            ).subscribe(
                result => {
                    localStorage.setItem('currentUser', JSON.stringify(result));
                    this.auth.next();
                    location.reload(true);
                },
                (e) => console.log(e)
            );
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.auth.next();
    }
}
