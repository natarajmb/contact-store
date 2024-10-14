package dev.cloudnative.contact.store.repository;

import dev.cloudnative.contact.store.model.Contact;

import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static dev.cloudnative.contact.store.support.ContactModelFactory.aContact;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * JPA test to validate persistence of the model {@link Contact}
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class ContactRepositoryTest {

    private static final String UUID_PATTERN = "^[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$";

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ContactRepository contactRepository;

    @Test
    void createContact() {
        Contact createdContact = contactRepository.saveAndFlush(aContact());
        assertThat(createdContact.getId().toString()).matches(UUID_PATTERN);
        assertThat(createdContact.getFirstName()).isEqualTo(aContact().getFirstName());
        assertThat(createdContact.getLastName()).isEqualTo(aContact().getLastName());
        assertThat(createdContact.getPhoneNumber()).isEqualTo(aContact().getPhoneNumber());
    }

    @Test
    void readContact() {
        Contact createdContact = contactRepository.saveAndFlush(aContact());
        entityManager.detach(createdContact);
        Contact readContact = contactRepository.findById(createdContact.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertThat(readContact).isEqualTo(createdContact);
    }

    @Test
    void updateContact() {
        Contact createdContact = contactRepository.saveAndFlush(aContact());
        entityManager.detach(createdContact);
        Contact changedContact = SerializationUtils.deserialize(SerializationUtils.serialize(createdContact));
        changedContact.setFirstName("changed");
        changedContact = contactRepository.saveAndFlush(changedContact);
        assertThat(changedContact).usingRecursiveComparison().ignoringFields("firstName")
                .isEqualTo(createdContact);
    }

    @Test
    void deleteContact() {
        Contact createdContact = contactRepository.saveAndFlush(aContact());
        entityManager.detach(createdContact);
        Contact readContact = contactRepository.findById(createdContact.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertThat(readContact).isEqualTo(createdContact);
        contactRepository.delete(readContact);
        entityManager.flush();
        assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() ->
                contactRepository.findById(createdContact.getId()).orElseThrow(EntityNotFoundException::new));
    }
}
