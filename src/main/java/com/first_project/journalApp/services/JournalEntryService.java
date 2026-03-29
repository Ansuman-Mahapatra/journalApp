package com.first_project.journalApp.services;

import com.first_project.journalApp.Repository.JournalEntryRepo;
import com.first_project.journalApp.entity.JournalEntry;
import com.first_project.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        Users user = userService.findByUsername(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepo.save(journalEntry);
//        user.setUsername(null);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }   

    public void save(JournalEntry journalEntry) {
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(@PathVariable ObjectId myId) {
        return journalEntryRepo.findById(myId);
    }

    public void deleteById(@PathVariable ObjectId myId, String username) {
        Users user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
        userService.saveUser(user);
        journalEntryRepo.deleteById(myId);
    }
}
