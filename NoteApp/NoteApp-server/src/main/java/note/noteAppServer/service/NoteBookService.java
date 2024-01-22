package note.noteAppServer.service;

import note.noteAppServer.model.NoteBook;

import java.util.List;
import java.util.Optional;

public interface NoteBookService {
    List<NoteBook> findAllNoteBooks();

    void deleteNoteBookById(long id);

    NoteBook saveNewNoteBook(NoteBook noteBook);

    Optional<NoteBook> findById(long id);
}
