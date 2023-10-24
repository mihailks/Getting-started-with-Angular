import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Notebook} from "../notes/model/notebook";
import {FeedbackViewModel} from "../feedback/feedback.component";
import {Note} from "../notes/model/note";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private BASE_URL = "http://localhost:8080/api";
  private ALL_NOTEBOOKS_URL = `${this.BASE_URL}/notebooks/all`;
  private SEND_FEEDBACK_URL = `${this.BASE_URL}/feedback`;
  private SAVE_NEW_NOTEBOOK = `${this.BASE_URL}/notebooks`;
  private DELETE_NOTEBOOK = `${this.BASE_URL}/notebooks`;
  private GET_ALL_NOTES = `${this.BASE_URL}/notes/all`;
  private GET_ALL_NOTES_PER_NOTEBOOK = `${this.BASE_URL}/notes`;
  private DELETE_NOTE = `${this.BASE_URL}/notes`;

  constructor(private http: HttpClient) {

  }

  getAllNotebooks(): Observable<Notebook[]> {
    return this.http.get<Notebook[]>(this.ALL_NOTEBOOKS_URL);
  }

  postFeedback(feedback: FeedbackViewModel): Observable<any> {
    return this.http.post(this.SEND_FEEDBACK_URL, feedback);
  }

  saveNotebook(notebook: Notebook): Observable<Notebook> {
    return this.http.post<Notebook>(this.SAVE_NEW_NOTEBOOK, notebook);
  }

  deleteNotebook(notebookId: number | null): Observable<any> {
    return this.http.delete(`${this.DELETE_NOTEBOOK}/${notebookId}`);
  }

  getAllNOtes():Observable<Note[]>{
    return this.http.get<Note[]>(this.GET_ALL_NOTES);
  }

  getNotesByNotebook(notebookId: string): Observable<Note[]>{
   return this.http.get<Note[]>(`${this.GET_ALL_NOTES_PER_NOTEBOOK}/${notebookId}`)
  }

  deleteNoteById(noteId: number | null):Observable<any> {
    return this.http.delete(`${this.DELETE_NOTE}/${noteId}`)
  }
}
