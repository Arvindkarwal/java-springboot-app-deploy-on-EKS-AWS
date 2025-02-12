package in.flowci.service;

import in.flowci.model.contact;
import in.flowci.repository.contactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ContactServiceTest {

    @Mock
    private contactRepository repository;

    @InjectMocks
    private contactService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllContacts() {
        // Arrange
        List<contact> contacts = Arrays.asList(
                new contact("John Doe", "john@example.com", "1234567890"),
                new contact("Jane Doe", "jane@example.com", "0987654321")
        );
        when(repository.getAllContacts()).thenReturn(contacts);

        // Act
        List<contact> result = service.getAllContacts();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(repository).getAllContacts();
    }

    @Test
    void testAddContact() {
        // Arrange
        contact newContact = new contact("John Doe", "john@example.com", "1234567890");

        // Act
        service.addContact(newContact);

        // Assert
        verify(repository).addContact(newContact);
    }

    @Test
    void testDeleteContact() {
        // Arrange
        contact contactToDelete = new contact("John Doe", "john@example.com", "1234567890");

        // Act
        service.deleteContact(contactToDelete);

        // Assert
        verify(repository).deleteContact(contactToDelete);
    }
}