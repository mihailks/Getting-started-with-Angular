import {Component, OnInit} from '@angular/core';
import {Notebook} from "./model/notebook";
import {ApiService} from "../shared/api.service";
import {Note} from "./model/note";
import {dateTimestampProvider} from "rxjs/internal/scheduler/dateTimestampProvider";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {
  notebooks: Notebook[] = [];
  notes: Note[] = [];
  selectedNotebook: Notebook | undefined;
  searchText: string;

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.getAllNotebooks(); //за да може, като си отворим страницата, да се покажат всички бележници
    this.getAllNotes()
  }

  getAllNotebooks() {
    this.apiService.getAllNotebooks().subscribe(
      res => { //реално респонса са ни обектите от тип Notebook
        this.notebooks = res;
      },
      err => {
        alert("An error has occurred while getting all notebooks");
      }
    );
  }

  getAllNotes() {
    this.apiService.getAllNOtes().subscribe(
      res => {
        this.notes = res
      },
      err => {
        alert("Error")
      }
    )
  }

  createNotebook() {
    let newNotebook: Notebook = {
      name: 'New notebook',
      id: null,
      nbOfNotes: 0
    }
    this.apiService.saveNotebook(newNotebook).subscribe(
      res => {
        newNotebook.id = res.id; //взимаме си id-то, което ни е сложила базата и го слагаме на нашия обект
        this.notebooks.push(newNotebook); //записваме си го в масива
      },
      err => {
        alert("An error has occurred while saving the notebook")
      }
    );
  }


  updateNotebook(updatedNotebook: Notebook) {
    //TODO: fix it with patch
    this.apiService.saveNotebook(updatedNotebook).subscribe(
      res => {
      },
      err => {
        alert("An error has occurred while updating the notebook")
      }
    );
  }

  deleteNotebook(notebook: Notebook) {
    if (confirm("Are you sure you want to delete it")) {
      this.apiService.deleteNotebook(notebook.id).subscribe(
        res => {
          let indexOfNotebook = this.notebooks.indexOf(notebook);
          this.notebooks.splice(indexOfNotebook, 1); //  splice - взима индекса на елемента, който искаме да изтрием и колко елемента искаме да изтрием
        },
        err => {
          alert("An error has occurred while deleting the notebook")
        }
      )
    }
  }

  private getAllNotesPerNotebook() {

  }

  deleteNote(note: Note) {
    if (confirm("Are you sure you want to delete this note")) {
      this.apiService.deleteNoteById(note.id).subscribe(
        res => {
          let indexToDelete = this.notes.indexOf(note);
          this.notes.splice(indexToDelete, 1);
        },
        err => {
          alert("An error has occurred while deleting the note")
        }
      )
    }
  }

  createNote(noteBookId: number | null | undefined) {
    let newNote: Note = {
      id: null,
      title: "new note",
      text: "content",
      lastModifiedOn: new Date(),
      notebookId: noteBookId
    }
    this.apiService.saveNewNote(newNote).subscribe(
      res => {
        newNote.id = res.id
        this.notes.push(newNote);
      },
      err => {
        alert("An error occurred while saving the note");
      }
    );

  }

  selectNotebook(notebook: Notebook) {
    this.selectedNotebook = notebook;

    this.apiService.getNotesByNotebook(notebook.id).subscribe(
      res => {
        this.notes = res;
      },
      err => {
        alert("An error occurred while grabbing notes");
      }
    );
  }

  updateNote(updatedNote: Note) {
    this.apiService.saveNewNote(updatedNote).subscribe(
      res => {
      },
      err => {
        alert("An error occurred while saving the note");
      }
    );
  }

  selectAllNotes() {
    this.selectedNotebook = undefined;
    this.getAllNotes();
  }
}
