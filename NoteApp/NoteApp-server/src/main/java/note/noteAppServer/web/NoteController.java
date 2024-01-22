package note.noteAppServer.web;

import jakarta.persistence.EntityNotFoundException;
import note.noteAppServer.model.NoteBook;
import note.noteAppServer.model.viewmodel.NoteViewModel;
import note.noteAppServer.model.Note;
import note.noteAppServer.repository.NoteBookRepository;
import note.noteAppServer.repository.NoteRepository;
import note.noteAppServer.service.NoteBookService;
import note.noteAppServer.service.NoteService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {
    private final NoteRepository noteRepository;
    private final NoteBookService noteBookService;
    private final NoteService noteService;
    private final ModelMapper modelMapper;

    public NoteController(NoteRepository noteRepository, NoteBookService noteBookService, NoteService noteService, ModelMapper modelMapper) {
        this.noteRepository = noteRepository;
        this.noteBookService = noteBookService;
        this.noteService = noteService;
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
        Optional<Note> note = this.noteService.findNoteById(Long.parseLong(id));

        if (note.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return this.modelMapper.map(note, NoteViewModel.class);
    }

    @GetMapping("/byNotebook/{notebookId}")
    public List<NoteViewModel> byNotebook(@PathVariable String notebookId) {
        List<Note> notes = new ArrayList<>();

        Optional<NoteBook> notebook = this.noteBookService.findById(Long.parseLong(notebookId));
        if (notebook.isPresent()) {
            notes = this.noteService.findAllByNotebook(notebook.get());
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

        return noteService.saveNote(this.modelMapper.map(noteCreateViewModel, Note.class));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.noteService.deleteNoteById(id);
    }
}
