package note.noteAppServer.model.viewmodel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NoteViewModel {
    private String id;

    @NotNull
    @Min(3)
    private String title;

    @NotNull
    private String text;

    @NotNull
    private String notebookId;

    private LocalDateTime lastModifiedOn;

    public NoteViewModel() {
    }

    public String getId() {
        return id;
    }

    public NoteViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NoteViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public NoteViewModel setText(String text) {
        this.text = text;
        return this;
    }

    public String getNotebookId() {
        return notebookId;
    }

    public NoteViewModel setNotebookId(String notebookId) {
        this.notebookId = notebookId;
        return this;
    }

    public LocalDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }

    public NoteViewModel setLastModifiedOn(LocalDateTime lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
        return this;
    }
}
