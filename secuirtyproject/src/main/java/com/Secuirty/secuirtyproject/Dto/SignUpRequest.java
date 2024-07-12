package com.Secuirty.secuirtyproject.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class SignUpRequest {
    private String firstname;
    private String lastname;
    @Email(message = "Invalid email format. Must be in the format yourname@anything.com")
    private String email;

    @Size(min = 4, max = 20, message = "Password length must be between 4 and 20 characters")
    private String password;
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
