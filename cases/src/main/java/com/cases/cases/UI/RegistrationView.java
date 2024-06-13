package com.cases.cases.UI;
import com.cases.cases.Models.Users;
import com.cases.cases.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Route("register")
public class RegistrationView extends VerticalLayout {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationView() {
        TextField usernameField = new TextField("Username");
        EmailField emailField = new EmailField("Email");
        TextField fnameField = new TextField("First Name");
        TextField lnameField = new TextField("Last Name");
        TextField phoneField = new TextField("Phone");
        TextField addressField = new TextField("Address");
        PasswordField passwordField = new PasswordField("Password");

        Button registerButton = new Button("Register", event -> {
            if (userService.findByUsername(usernameField.getValue()) != null) {
                Notification.show("Username already taken");
                return;
            }

            Users user = new Users();
            user.setUsername(usernameField.getValue());
            user.setEmail(emailField.getValue());
            user.setFname(fnameField.getValue());
            user.setLname(lnameField.getValue());
            user.setPhone(Long.parseLong(phoneField.getValue()));
            user.setAddress(addressField.getValue());
            user.setPassword(passwordEncoder.encode(passwordField.getValue()));

            userService.save(user);
            Notification.show("Registration successful");

            // Redirect to login page
            getUI().ifPresent(ui -> ui.navigate("login"));
        });

        add(usernameField, emailField, fnameField, lnameField, phoneField, addressField, passwordField, registerButton);
    }
}
