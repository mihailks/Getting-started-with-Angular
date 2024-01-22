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
    private final NoteBookRepository notebookRepository;
    private final NoteBookService noteBookService;
    private final ModelMapper modelMapper;

    public NoteBookController(NoteBookRepository notebookRepository, NoteBookService noteBookService, ModelMapper modelMapper) {
        this.notebookRepository = notebookRepository;
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

        var notebookEntity = this.modelMapper.map(notebookViewModel, NoteBook.class);

        this.notebookRepository.save(notebookEntity);

        return notebookEntity;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.notebookRepository.deleteById(Long.valueOf(id));
    }
}
