package in.flowci.service;

import in.flowci.model.contact;
import in.flowci.repository.contactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class contactService {

    private final contactRepository repository;

    public contactService(contactRepository repository) {
        this.repository = repository;
    }

    public List<contact> getAllContacts() {
        return repository.getAllContacts();
    }

    public void addContact(contact contact) {
        repository.addContact(contact);
    }

    // Method to delete a contact
    public void deleteContact(contact contact) {
        repository.deleteContact(contact);
    }
}
