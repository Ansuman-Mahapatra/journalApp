package com.first_project.journalApp.Controller;

import com.first_project.journalApp.entity.JournalEntry;
import com.first_project.journalApp.services.JournalEntryService;
import com.first_project.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.first_project.journalApp.entity.Users;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void CreateUser(@RequestBody Users user){
        userService.saveUser(user);
    }

    @PutMapping("/{username}")
    public void UpdateUser(@RequestBody Users user, @PathVariable String username){
        Users userInDB = userService.findByUsername(username);

        if(userInDB != null){
            userInDB.setUsername(user.getUsername());
            userInDB.setPassword(user.getPassword());
        }
        userService.saveUser(userInDB);
    }
}
