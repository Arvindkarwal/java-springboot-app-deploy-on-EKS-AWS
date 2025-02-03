package in.flowci.contactcontroller;

import in.flowci.model.contact;
import in.flowci.service.contactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class contactControllerTest {

    @Mock
    private contactService service;

    @Mock
    private Model model;

    @InjectMocks
    private contactController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHome() {
        // Arrange
        List<contact> contacts = Arrays.asList(
                new contact("John Doe", "john@example.com", "1234567890"),
                new contact("Jane Doe", "jane@example.com", "0987654321")
        );
        when(service.getAllContacts()).thenReturn(contacts);

        // Act
        String viewName = controller.home(model);

        // Assert
        assertEquals("index", viewName);
        verify(model).addAttribute("contacts", contacts);
        verify(service).getAllContacts();
    }

    @Test
    void testAbout() {
        // Act
        String viewName = controller.about();

        // Assert
        assertEquals("about", viewName);
    }

    @Test
    void testAddContact() {
        // Arrange
        contact newContact = new contact("John Doe", "john@example.com", "1234567890");

        // Act
        String viewName = controller.addContact(newContact);

        // Assert
        assertEquals("redirect:/", viewName);
        verify(service).addContact(newContact);
    }

    @Test
    void testDeleteContact() {
        // Arrange
        contact contactToDelete = new contact("John Doe", "john@example.com", "1234567890");

        // Act
        String viewName = controller.deleteContact(contactToDelete);

        // Assert
        assertEquals("redirect:/", viewName);
        verify(service).deleteContact(contactToDelete);
    }
}