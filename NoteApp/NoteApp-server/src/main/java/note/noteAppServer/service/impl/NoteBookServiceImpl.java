package note.noteAppServer.service.impl;

import note.noteAppServer.model.NoteBook;
import note.noteAppServer.repository.NoteBookRepository;
import note.noteAppServer.service.NoteBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteBookServiceImpl implements NoteBookService {
    private final NoteBookRepository noteBookRepository;

    public NoteBookServiceImpl(NoteBookRepository noteBookRepository) {
        this.noteBookRepository = noteBookRepository;
    }

    @Override
    public List<NoteBook> findAllNoteBooks() {
        return noteBookRepository.findAll();
    }

    @Override
    public void deleteNoteBookById(long id) {
        this.noteBookRepository.deleteById(id);
    }

    @Override
    public NoteBook saveNewNoteBook(NoteBook noteBook) {
        return this.noteBookRepository.save(noteBook);
    }

    @Override
    public Optional<NoteBook> findById(long id) {
        return noteBookRepository.findById(id);
    }
}
