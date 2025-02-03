package in.flowci.contactcontroller;


import in.flowci.model.contact;
import in.flowci.service.contactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class contactController {

    private final contactService service;

    public contactController(contactService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("contacts", service.getAllContacts());
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @PostMapping("/addContact")
    public String addContact(@ModelAttribute contact contact) {
        service.addContact(contact);
        return "redirect:/";
    }

    // Delete contact handler
    @PostMapping("/deleteContact")
    public String deleteContact(@ModelAttribute contact contact) {
        service.deleteContact(contact);
        return "redirect:/";
    }
}
