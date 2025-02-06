package in.flowci.a_contactmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "in.flowci.contactcontroller",
        "in.flowci.service",
        "in.flowci.repository",
        "in.flowci.model"
})
public class contactManagerApplication{
    public static void main(String[] args) {
        // code comment -- 04
        SpringApplication app = new SpringApplication(contactManagerApplication.class);
        app.run(args);
    }

}
