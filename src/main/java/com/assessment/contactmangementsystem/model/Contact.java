package com.assessment.contactmangementsystem.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contact {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
