package com.boukhoulda.Dermachat.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Message {
    @Getter
    @Setter
    String message;

    @Getter
    @Setter
    String sender;

    public Message() {}

}
