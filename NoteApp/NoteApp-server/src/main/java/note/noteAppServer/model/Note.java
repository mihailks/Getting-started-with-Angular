package note.noteAppServer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note extends BaseEntity{

    private String title;
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    private NoteBook notebook;
    private LocalDateTime lastModifiedOn;

    public Note() {
    }

    public Note(String defaultName) {
        this.title = defaultName;
    }


    public String getTitle() {
        return title;
    }

    public Note setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public Note setText(String text) {
        this.text = text;
        return this;
    }

    public NoteBook getNotebook() {
        return notebook;
    }

    public Note setNotebook(NoteBook notebook) {
        this.notebook = notebook;
        return this;
    }

    public LocalDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }

    public Note setLastModifiedOn(LocalDateTime lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
        return this;
    }
}
