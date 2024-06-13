package com.cases.cases.UI;

import com.cases.cases.Models.Users;
import com.cases.cases.Services.CommentService;
import com.cases.cases.Services.SupportCaseService;
import com.cases.cases.Services.UserService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("account-info")
public class AccountInfoView extends VerticalLayout {

    private final UserService userService;
    private final SupportCaseService supportCaseService;
    private final CommentService commentService;

    @Autowired
    public AccountInfoView(UserService userService, SupportCaseService supportCaseService, CommentService commentService) {
        this.userService = userService;
        this.supportCaseService = supportCaseService;
        this.commentService = commentService;

        Users authenticatedUser = userService.getAuthenticatedUser();
        if (authenticatedUser == null) {
            // Handle case where no authenticated user is found, possibly redirect to login
            return;
        }

        TextField usernameField = new TextField("Username", authenticatedUser.getUsername());
        TextField emailField = new TextField("Email", authenticatedUser.getEmail());
        TextField fnameField = new TextField("First Name", authenticatedUser.getFname());
        TextField lnameField = new TextField("Last Name", authenticatedUser.getLname());
        TextField phoneField = new TextField("Phone", String.valueOf(authenticatedUser.getPhone()));
        TextField addressField = new TextField("Address", authenticatedUser.getAddress());

        int caseCount = supportCaseService.getCasesByUserId(authenticatedUser.getId()).size();
        int commentCount = commentService.getCommentsByUserId(authenticatedUser.getId()).size();

        TextField casesField = new TextField("Number of Cases", String.valueOf(caseCount));
        TextField commentsField = new TextField("Number of Comments", String.valueOf(commentCount));

        // Add sidebar
        Sidebar sidebar = new Sidebar();
        add(sidebar, usernameField, emailField, fnameField, lnameField, phoneField, addressField, casesField, commentsField);
    }
}
