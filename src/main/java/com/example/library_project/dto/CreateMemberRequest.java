package com.example.library_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateMemberRequest {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email must not be blank")
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
