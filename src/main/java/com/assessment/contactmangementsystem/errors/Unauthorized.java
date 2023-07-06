package com.assessment.contactmangementsystem.errors;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder

public class Unauthorized implements Serializable {
    private int status;
    private String message;
}
