package com.boukhoulda.Dermachat.models;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Document("users")
public class User {

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String password;

    public User() {

    }


}
