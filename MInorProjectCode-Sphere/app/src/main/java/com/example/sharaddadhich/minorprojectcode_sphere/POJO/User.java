package com.example.sharaddadhich.minorprojectcode_sphere.POJO;

/**
 * Created by sharaddadhich on 13/09/17.
 */

public class User {
    String Name,Username,Email,E_no,Password;

    public User(String name, String username, String email, String e_no, String password) {
        Name = name;
        Username = username;
        Email = email;
        E_no = e_no;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getE_no() {
        return E_no;
    }

    public void setE_no(String e_no) {
        E_no = e_no;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
