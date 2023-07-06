package com.assessment.contactmangementsystem.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadRequest {
    private int status;
    private String message;
}
