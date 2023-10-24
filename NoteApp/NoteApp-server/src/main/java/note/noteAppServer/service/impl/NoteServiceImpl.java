package note.noteAppServer.service.impl;

import note.noteAppServer.repository.NoteRepository;
import note.noteAppServer.service.NoteService;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
}
