package dev.cloudnative.contact.store.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Entity
@Table(name = "contact")
@Data
@Builder
@AllArgsConstructor
public class Contact implements Serializable {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Tolerate
    public Contact() {
        // no args constructor is required for JPA which conflicts with lombok Builder
    }
}
