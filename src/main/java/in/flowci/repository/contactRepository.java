package in.flowci.repository;

import in.flowci.model.contact;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class contactRepository {
    private final List<contact> contacts = new ArrayList<>();

    public List<contact> getAllContacts() {
        return contacts;
    }

    public void addContact(contact contact) {
        contacts.add(contact);
    }

    // Method to delete a contact
    public void deleteContact(contact contact) {
        contacts.remove(contact);
    }
}
