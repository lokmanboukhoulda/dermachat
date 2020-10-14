package com.boukhoulda.Dermachat.security.service;

import com.boukhoulda.Dermachat.security.model.MongoUserDetails;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MongoClient mongoClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MongoDatabase db = mongoClient.getDatabase("dermachat");
        MongoCollection<Document> collection = db.getCollection("users");
        Document document = collection.find(Filters.eq("email", email)).first();

        if (document != null) {
            String name = document.getString("name");
            String surname = document.getString("surname");
            String password = document.getString("password");
            List<String> authorities = (List<String>) document.get("authorities");
            System.out.println(authorities.toString());
            System.out.println("Hello World");
            MongoUserDetails mongoUserDetails = new MongoUserDetails(email, password, authorities.toString());

            return mongoUserDetails;

        }

        return null;
    }
}
