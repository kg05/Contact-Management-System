package com.assessment.contactmangementsystem.controller;

import com.assessment.contactmangementsystem.errors.BadRequest;
import com.assessment.contactmangementsystem.errors.Unauthorized;
import com.assessment.contactmangementsystem.model.Contact;
import com.assessment.contactmangementsystem.pojo.ContactRequest;
import com.assessment.contactmangementsystem.pojo.ContactResponse;
import com.assessment.contactmangementsystem.security.JWTService;
import com.assessment.contactmangementsystem.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @Autowired
    JWTService jwtService;

    @PostMapping
    public ResponseEntity create(
            @Valid @RequestBody ContactRequest request,
            @RequestHeader (name="auth-token") String token
    ) {
        Contact contact = contactService.findByEmailOrNumber(request.getEmail(), request.getPhoneNumber(), null);
        if (contact != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BadRequest.builder()
                            .status(400)
                            .message("Contact Already Exists")
                            .build()
                    );
        }
        return ResponseEntity.ok(contactService.create(request));
    }

    @GetMapping
    public ResponseEntity find(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestHeader (name="auth-token") String token
    ) {
        return ResponseEntity.ok(contactService.find(firstName, lastName, email));
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(
            @PathVariable String id,
            @RequestHeader (name="auth-token") String token
    ) {
        ContactResponse contactResponse = contactService.findById(id);
        if (contactResponse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BadRequest.builder()
                            .status(400)
                            .message("Id not found")
                            .build()
                    );
        }
        return  ResponseEntity.ok(contactResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(
            @Valid @RequestBody ContactRequest request,
            @PathVariable String id,
            @RequestHeader (name="auth-token") String token
    ) {
        ContactResponse contactResponse = contactService.findById(id);
        if (contactResponse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BadRequest.builder()
                            .status(400)
                            .message("Id not found")
                            .build()
                    );
        }
        Contact contact = contactService.findByEmailOrNumber(request.getEmail(), request.getPhoneNumber(), id);
        if (contact != null && !contact.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BadRequest.builder()
                            .status(400)
                            .message("Contact Already Exists")
                            .build()
                    );
        }
        return ResponseEntity.ok(contactService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable String id,
            @RequestHeader (name="auth-token") String token
    ) {
        ContactResponse contactResponse = contactService.findById(id);
        if (contactResponse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(BadRequest.builder()
                            .status(400)
                            .message("Id not found")
                            .build()
                    );
        }
        return ResponseEntity.ok(contactService.delete(id));
    }
}
