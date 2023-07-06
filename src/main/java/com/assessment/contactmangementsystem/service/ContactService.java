package com.assessment.contactmangementsystem.service;

import com.assessment.contactmangementsystem.model.Contact;
import com.assessment.contactmangementsystem.pojo.ContactRequest;
import com.assessment.contactmangementsystem.pojo.ContactResponse;
import com.assessment.contactmangementsystem.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ContactService {

    @Autowired
    ContactRepository repository;

    public ContactResponse create(ContactRequest request) {
        Contact contact = Contact.builder()
                .id(UUID.randomUUID().toString())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();
        return createContactResponse(repository.createContact(contact));
    }

    public List<ContactResponse> find(String firstName, String lastName, String email) {
        List<Contact> contacts = repository.getContacts(firstName, lastName, email);
        List<ContactResponse> contactResponses = new ArrayList<>();
        for(Contact contact: contacts) {
            contactResponses.add(createContactResponse(contact));
        }
        return contactResponses;
    }

    public ContactResponse update(String id, ContactRequest request) {
        Contact contact = Contact.builder()
                .id(id)
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();

        return createContactResponse(repository.updateContact(id, contact));
    }
    public String delete(String id) {
        return repository.deleteContact(id);
    }

    public ContactResponse findById(String id) {
        Contact contact = repository.findById(id);
        if(contact == null)
            return null;
        return createContactResponse(repository.findById(id));
    }

    public Contact findByEmailOrNumber(String email, String phoneNumber, String id) {
        return repository.findByEmailOrNumber(email, phoneNumber, id);
    }
    private ContactResponse createContactResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .email(contact.getEmail())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .phoneNumber(contact.getPhoneNumber())
                .build();
    }
}
