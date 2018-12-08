import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class WriteStoryService {
    constructor(private http: HttpClient) {
    }

    public postStory(title: string, intro: string, description: string): Observable<any> {
        const data = JSON.stringify(
            {
                'title': title,
                'intro': intro,
                'text': description
            }
        )
        let headers: HttpHeaders = new HttpHeaders();
        headers = headers.append( 'Content-Type', 'application/json; charset=utf-8' );

        return this.http.post('/api/story', data, {headers});
    }
}