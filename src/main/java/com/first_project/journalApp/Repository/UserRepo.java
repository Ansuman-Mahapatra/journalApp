package com.first_project.journalApp.Repository;

import com.first_project.journalApp.entity.JournalEntry;
import com.first_project.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<Users, ObjectId> {
    Users findByUsername (String username);
}
