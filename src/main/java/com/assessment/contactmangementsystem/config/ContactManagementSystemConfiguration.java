package com.assessment.contactmangementsystem.config;

import com.assessment.contactmangementsystem.repository.ContactRepository;
import com.assessment.contactmangementsystem.security.JWTService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactManagementSystemConfiguration {
    @Bean
    public ContactRepository createContactRepository() {
        return new ContactRepository();
    }

    @Bean
    public JWTService getJWT() {
        return new JWTService();
    }
}
