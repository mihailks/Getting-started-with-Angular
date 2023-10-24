package note.noteAppServer.init;

import note.noteAppServer.model.Note;
import note.noteAppServer.model.NoteBook;
import note.noteAppServer.repository.NoteBookRepository;
import note.noteAppServer.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final NoteRepository noteRepository;
    private final NoteBookRepository noteBookRepository;

    public DBInit(NoteRepository noteRepository, NoteBookRepository noteBookRepository) {
        this.noteRepository = noteRepository;
        this.noteBookRepository = noteBookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (noteBookRepository.count() == 0 && noteRepository.count() == 0) {

            var defaultNoteBook = noteBookRepository.save(new NoteBook("Default"));
            this.noteBookRepository.save(defaultNoteBook);

            var defaultNote = noteRepository.save(new Note("Default"));
            this.noteRepository.save(defaultNote);


        }


    }
}
