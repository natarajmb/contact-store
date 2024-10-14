package dev.cloudnative.contact.store;

import dev.cloudnative.contact.store.model.Contact;
import dev.cloudnative.contact.store.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(ContactRepository contactRepository) {
        return (args) -> {
            contactRepository.save(Contact.builder().firstName("Paul").lastName("Pipey").phoneNumber("11111111").build());
            contactRepository.save(Contact.builder().firstName("Richard").lastName("Sparkie").phoneNumber("22222222").build());
            contactRepository.save(Contact.builder().firstName("Chris").lastName("Chippy").phoneNumber("33333333").build());
            contactRepository.save(Contact.builder().firstName("Rachel").lastName("Doc").phoneNumber("44444444").build());
            contactRepository.save(Contact.builder().firstName("Doris").lastName("Professor").phoneNumber("55555555").build());
        };
    }


}
