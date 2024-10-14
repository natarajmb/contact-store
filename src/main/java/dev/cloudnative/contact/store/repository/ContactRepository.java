package dev.cloudnative.contact.store.repository;

import dev.cloudnative.contact.store.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;


/**
 * Repository for {@link Contact} database operations
 */
@RepositoryRestResource(collectionResourceRel = "contacts", path = "contacts")
public interface ContactRepository extends JpaRepository<Contact, UUID> {

}
