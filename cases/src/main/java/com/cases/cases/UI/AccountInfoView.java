package com.cases.cases.UI;

import com.cases.cases.Models.Users;
import com.cases.cases.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("account-info")
public class AccountInfoView extends VerticalLayout {

    @Autowired
    private UserService userService;

    public AccountInfoView() {
        Users currentUser = userService.findByUsername("currentLoggedInUser"); // Get current logged-in user

        TextField usernameField = new TextField("Username", currentUser.getUsername());
        TextField emailField = new TextField("Email", currentUser.getEmail());
        Button updateButton = new Button("Update", event -> {
            currentUser.setUsername(usernameField.getValue());
            currentUser.setEmail(emailField.getValue());
            userService.save(currentUser);
            Notification.show("Account information updated successfully");
        });

        add(usernameField, emailField, updateButton);
    }
}
