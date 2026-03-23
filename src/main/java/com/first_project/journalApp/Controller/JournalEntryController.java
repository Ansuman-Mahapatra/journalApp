package com.first_project.journalApp.Controller;

import com.first_project.journalApp.entity.JournalEntry;
import com.first_project.journalApp.entity.Users;
import com.first_project.journalApp.services.JournalEntryService;
import com.first_project.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalEntriesByUser(@PathVariable String username) {
        Users user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username) {
        try{
            journalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String username) {
        journalEntryService.deleteById(myId, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{username}/{myId}")
    public ResponseEntity<?> updateJournalEntryById(
            @PathVariable ObjectId myId,
            @RequestBody JournalEntry newJournalEntry,
            @PathVariable String username
    ) {
        JournalEntry oldEntry = journalEntryService.findById(myId).orElse(newJournalEntry);
        if(oldEntry != null){
            oldEntry.setTitle(newJournalEntry.getTitle() !=null && !newJournalEntry.getTitle().isEmpty() ? newJournalEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newJournalEntry.getContent() !=null && !newJournalEntry.getContent().isEmpty() ? newJournalEntry.getContent() : oldEntry.getContent());
            journalEntryService.save(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
