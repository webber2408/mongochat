package com.example.sharaddadhich.minorprojectcode_sphere.POJO;

/**
 * Created by sharaddadhich on 25/09/17.
 */

public class ChatData {

    String name,message;

    public ChatData(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

