package note.noteAppServer.web;

import jakarta.persistence.EntityNotFoundException;
import note.noteAppServer.model.viewmodel.NoteViewModel;
import note.noteAppServer.model.Note;
import note.noteAppServer.repository.NoteBookRepository;
import note.noteAppServer.repository.NoteRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {
    private final NoteRepository noteRepository;
    private final NoteBookRepository notebookRepository;
    private final ModelMapper modelMapper;

    public NoteController(NoteRepository noteRepository, NoteBookRepository notebookRepository, ModelMapper modelMapper) {
        this.noteRepository = noteRepository;
        this.notebookRepository = notebookRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<NoteViewModel> all() {
        var notes = this.noteRepository.findAll();

        return notes.stream()
                .map(note -> this.modelMapper.map(note, NoteViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/byId/{id}")
    public NoteViewModel byId(@PathVariable String id) {
        var note = this.noteRepository.findById(Long.valueOf(id)).orElse(null);

        if (note == null) {
            throw new EntityNotFoundException();
        }

        return this.modelMapper.map(note, NoteViewModel.class);
    }

    @GetMapping("/byNotebook/{notebookId}")
    public List<NoteViewModel> byNotebook(@PathVariable String notebookId) {
        List<Note> notes = new ArrayList<>();

        var notebook = this.notebookRepository.findById(Long.valueOf(notebookId));
        if (notebook.isPresent()) {
            notes = this.noteRepository.findAllByNotebook(notebook.get());
        }

        return notes.stream()
                .map(note -> this.modelMapper.map(note, NoteViewModel.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Note save(@RequestBody NoteViewModel noteCreateViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException();
        }

        var noteEntity = this.modelMapper.map(noteCreateViewModel, Note.class);

        // save note instance to db
        this.noteRepository.save(noteEntity);

        return noteEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.noteRepository.deleteById(Long.valueOf(id));
    }
}
