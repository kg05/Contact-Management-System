package com.assessment.contactmangementsystem.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 10, max = 13)
    private String phoneNumber;
}
