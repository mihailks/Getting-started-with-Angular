package note.noteAppServer.service;

import note.noteAppServer.model.Note;
import note.noteAppServer.model.NoteBook;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    List<Note> findAllByNotebook(NoteBook noteBook);

    Optional<Note> findNoteById(long id);

    Note saveNote(Note note);

    void deleteNoteById(String id);
}
