package note.noteAppServer.service.impl;

import note.noteAppServer.model.Note;
import note.noteAppServer.model.NoteBook;
import note.noteAppServer.repository.NoteRepository;
import note.noteAppServer.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findAllByNotebook(NoteBook noteBook) {
        return noteRepository.findAllByNotebook(noteBook);
    }

    @Override
    public Optional<Note> findNoteById(long id) {
        return noteRepository.findById(id);
    }

    @Override
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteById(String id) {
        noteRepository.deleteById(Long.parseLong(id));
    }
}
