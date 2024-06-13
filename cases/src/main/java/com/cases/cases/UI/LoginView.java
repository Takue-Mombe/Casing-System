package com.cases.cases.UI;
import com.cases.cases.Models.Users;
import com.cases.cases.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Route("login")
public class LoginView extends VerticalLayout {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginView() {
        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");
        Button loginButton = new Button("Login", event -> {
            Users user = userService.findByUsername(usernameField.getValue());
            if (user != null && passwordEncoder.matches(passwordField.getValue(), user.getPassword())) {
                Notification.show("Login successful");
                // Redirect to dashboard
            } else {
                Notification.show("Invalid credentials");
            }
        });

        Button registerButton = new Button("Register", event -> {
            // Redirect to registration page
            getUI().ifPresent(ui -> ui.navigate("register"));
        });

        add(usernameField, passwordField, loginButton, registerButton);
    }
}
