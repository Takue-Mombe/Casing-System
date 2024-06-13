package com.cases.cases.UI;

import com.cases.cases.Models.Role;
import com.cases.cases.Models.Users;
import com.cases.cases.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@Route("register")
public class RegistrationView extends VerticalLayout {

    private final UserService userService;

    @Autowired
    public RegistrationView(UserService userService) {
        this.userService = userService;

        TextField userIdField = new TextField("User Id");
        userIdField.setReadOnly(true);
        userIdField.setValue(generateRandomUserId());

        TextField usernameField = new TextField("Username");
        EmailField emailField = new EmailField("Email");
        TextField fnameField = new TextField("First Name");
        TextField lnameField = new TextField("Last Name");
        TextField phoneField = new TextField("Phone");
        TextField addressField = new TextField("Address");
        TextField passwordField = new TextField("Password");

        Button registerButton = new Button("Register", event -> {
            String username = usernameField.getValue();
            if (userService.getUserByUsername(username) != null) {
                Notification.show("Username already taken");
                return;
            }

            Users user = new Users();
            user.setUsername(username);
            user.setEmail(emailField.getValue());
            user.setFname(fnameField.getValue());
            user.setLname(lnameField.getValue());
            user.setPhone(Long.parseLong(phoneField.getValue()));
            user.setAddress(addressField.getValue());
            user.setRole(Role.USER);
            user.setPassword(passwordField.getValue()); // Store plain text password

            userService.saveUser(user);
            Notification.show("Registration successful");

            getUI().ifPresent(ui -> ui.navigate("login"));
        });

        add(userIdField, usernameField, emailField, fnameField, lnameField, phoneField, addressField, passwordField, registerButton);
    }

    private String generateRandomUserId() {
        Random random = new Random();
        int userId = random.nextInt(10000); // Generates a random integer between 0 (inclusive) and 10000 (exclusive)
        return String.valueOf(userId);
    }
}
