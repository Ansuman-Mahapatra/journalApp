package com.first_project.journalApp.services;

import com.first_project.journalApp.Repository.JournalEntryRepo;
import com.first_project.journalApp.Repository.UserRepo;
import com.first_project.journalApp.entity.JournalEntry;
import com.first_project.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void saveUser(Users user) {
        userRepo.save(user);
    }

    public List<Users> getAll() {
        return userRepo.findAll();
    }

    public Optional<Users> findById(@PathVariable ObjectId myId) {
        return userRepo.findById(myId);
    }

    public void deleteById(@PathVariable ObjectId myId) {
        userRepo.deleteById(myId);
    }

    public Users findByUsername(@PathVariable String username) {
        return userRepo.findByUsername(username);
    }
}
