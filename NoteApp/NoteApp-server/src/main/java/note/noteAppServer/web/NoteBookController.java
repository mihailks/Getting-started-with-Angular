package note.noteAppServer.web;

import jakarta.validation.ValidationException;
import note.noteAppServer.model.viewmodel.NotebookViewModel;
import note.noteAppServer.model.NoteBook;
import note.noteAppServer.repository.NoteBookRepository;
import note.noteAppServer.service.NoteBookService;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NoteBookController {
    private final NoteBookService noteBookService;
    private final ModelMapper modelMapper;

    public NoteBookController( NoteBookService noteBookService, ModelMapper modelMapper) {
        this.noteBookService = noteBookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<NoteBook> all() {
        return this.noteBookService.findAllNoteBooks();
    }

    @PostMapping
    public NoteBook save(@RequestBody NotebookViewModel notebookViewModel,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        return this.noteBookService.saveNewNoteBook(this.modelMapper.map(notebookViewModel, NoteBook.class));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.noteBookService.deleteNoteBookById(Long.parseLong(id));
    }
}
