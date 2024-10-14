package dev.cloudnative.contact.store.support;

import dev.cloudnative.contact.store.model.Contact;

/**
 * Factory class to create test data for model classes used in unit tests
 */
public class ContactModelFactory {

    public static Contact aContact() {
        return Contact.builder()
                .firstName("Tommy")
                .lastName("Hilfiger")
                .phoneNumber("0787878787")
                .build();
    }
}
