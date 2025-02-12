package in.flowci.contactRepository;

import in.flowci.model.contact;
import in.flowci.repository.contactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactRepositoryTest {

    private contactRepository repository;

    @BeforeEach
    void setUp() {
        repository = new contactRepository();
    }

    @Test
    void testGetAllContacts() {
        // Act
        List<contact> contacts = repository.getAllContacts();

        // Assert
        assertNotNull(contacts);
        assertEquals(0, contacts.size());
    }

    @Test
    void testAddContact() {
        // Arrange
        contact newContact = new contact("John Doe", "john@example.com", "1234567890");

        // Act
        repository.addContact(newContact);
        List<contact> contacts = repository.getAllContacts();

        // Assert
        assertEquals(1, contacts.size());
        assertEquals("John Doe", contacts.get(0).getName());
    }

    @Test
    void testDeleteContact() {
        // Arrange
        contact contact1 = new contact("John Doe", "john@example.com", "1234567890");
        contact contact2 = new contact("Jane Doe", "jane@example.com", "0987654321");
        repository.addContact(contact1);
        repository.addContact(contact2);

        // Act
        repository.deleteContact(contact1);
        List<contact> contacts = repository.getAllContacts();

        // Assert
        assertEquals(1, contacts.size());
        assertEquals("Jane Doe", contacts.get(0).getName());
    }
}