package note.noteAppServer.repository;

import note.noteAppServer.model.Note;
import note.noteAppServer.model.NoteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByNotebook(NoteBook notebook);

}
