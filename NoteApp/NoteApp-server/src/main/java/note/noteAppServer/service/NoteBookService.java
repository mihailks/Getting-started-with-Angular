package note.noteAppServer.service;

import note.noteAppServer.model.NoteBook;

import java.util.List;

public interface NoteBookService {
    List<NoteBook> findAllNoteBooks();

}
