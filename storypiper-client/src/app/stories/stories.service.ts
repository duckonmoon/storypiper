import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class StoryService {
    constructor(private http: HttpClient) {
    }

    getStories(): Observable<any> {
        return this.http.get('api/user/hey');
    }
}