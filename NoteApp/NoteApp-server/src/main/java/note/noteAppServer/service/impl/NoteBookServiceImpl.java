package note.noteAppServer.service.impl;

import note.noteAppServer.repository.NoteBookRepository;
import note.noteAppServer.service.NoteBookService;
import org.springframework.stereotype.Service;

@Service
public class NoteBookServiceImpl implements NoteBookService {
    private final NoteBookRepository noteBookRepository;

    public NoteBookServiceImpl(NoteBookRepository noteBookRepository) {
        this.noteBookRepository = noteBookRepository;
    }
}
