package com.cases.cases.UI;

import com.cases.cases.Models.Role;
import com.cases.cases.Models.Users;
import com.cases.cases.Services.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "login")
@AnonymousAllowed
@PageTitle("Login")
public class LoginView extends VerticalLayout {

    private final UserService authService;

    @Autowired
    public LoginView(UserService authService) {
        this.authService = authService;
        setId("login-view");

        // Username field
        TextField usernameField = new TextField("Username");
        usernameField.setPlaceholder("Enter your username");

        // Password field
        PasswordField passwordField = new PasswordField("Password");
        passwordField.setPlaceholder("Enter your password");
        RouterLink registrationLink = new RouterLink("Register here", RegistrationView.class);

        // Login button
        Button loginButton = new Button("Login", event -> {
            try {
                Users authenticatedUser = authService.authenticate(usernameField.getValue(), passwordField.getValue());
                navigateToRoleSpecificView(authenticatedUser);
            } catch (UserService.AuthException e) {
                Notification.show("Wrong Credentials");
            }
        });

        add(
                new H1("Welcome"),
                usernameField,
                passwordField,
                loginButton,
                registrationLink
        );

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void navigateToRoleSpecificView(Users authenticatedUser) {
        if (authenticatedUser != null && authenticatedUser.getRole() != null) {
            if (authenticatedUser.getRole().equals(Role.USER)) {
                UI.getCurrent().getSession().setAttribute("user", authenticatedUser);
                UI.getCurrent().navigate("dashboard");
            } else if (authenticatedUser.getRole().equals(Role.ADMIN)) {
                UI.getCurrent().getSession().setAttribute("admin", authenticatedUser);
                UI.getCurrent().navigate("dashboard");
            }
        } else {
            // Handle case where role is not available
            Notification.show("Role not found");
        }
    }
}
