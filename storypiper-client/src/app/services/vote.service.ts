import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class VoteService {

    constructor(private http: HttpClient) {
    }

    public vote(storyId: number, up: boolean): Observable<any> {
        return this.http.post(`/api/vote?storyId=${storyId}&up=${up}`, null);
    }
}
