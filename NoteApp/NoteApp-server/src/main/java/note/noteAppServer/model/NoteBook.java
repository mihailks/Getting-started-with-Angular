package note.noteAppServer.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "notebooks")
public class NoteBook extends BaseEntity {
        private String name;
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "notebook",cascade = CascadeType.ALL)
        private List<Note> notes;


    public NoteBook() {
    }

    public NoteBook(String defaultName) {
        this.name = defaultName;
    }

    public String getName() {
        return name;
    }

    public NoteBook setName(String name) {
        this.name = name;
        return this;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public NoteBook setNotes(List<Note> notes) {
        this.notes = notes;
        return this;
    }
}
