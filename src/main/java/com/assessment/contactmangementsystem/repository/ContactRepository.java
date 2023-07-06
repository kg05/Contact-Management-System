package com.assessment.contactmangementsystem.repository;

import com.assessment.contactmangementsystem.model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContactRepository {
    Map<String, Contact> contactDB = new HashMap<>();
    public Contact createContact(Contact contact) {
        contactDB.put(contact.getId(), contact);
        return contact;
    }

    public List<Contact> getContacts(String firstName, String lastName, String email) {
        List<Contact> contacts = new ArrayList<>();
        for (Map.Entry<String, Contact> entry : contactDB.entrySet()) {
            Contact contact = entry.getValue();
            if(firstName != null && !contact.getFirstName().equals(firstName))
                continue;
            if(lastName != null && !contact.getLastName().equals(lastName))
                continue;
            if(email != null && !contact.getEmail().equals(email))
                continue;
            contacts.add(contact);
        }
        return contacts;
    }

    public Contact updateContact(String id, Contact contact) {
        contactDB.put(id, contact);
        return contact;
    }

    public String deleteContact(String id) {
        contactDB.remove(id);
        return "Deleted!!";
    }

    public Contact findById(String id) {
        if(contactDB.containsKey(id))
            return contactDB.get(id);
        return null;
    }

    public Contact findByEmailOrNumber(String email, String phoneNumber, String id) {
        for (Map.Entry<String, Contact> entry : contactDB.entrySet()) {
            Contact contact = entry.getValue();
            if(id != null && contact.getId().equals(id))
                continue;
            if(contact.getEmail().equals(email)) {
                return contact;
            }
            if(contact.getPhoneNumber().equals(phoneNumber)) {
                return contact;
            }
        }
        return null;
    }
}
