package com.cases.cases.UI;

import com.cases.cases.Models.Comment;
import com.cases.cases.Models.SupportCase;
import com.cases.cases.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Route("dashboard")
public class DashboardView extends HorizontalLayout {

    private final UserService userService;

    private Grid<SupportCase> caseGrid;
    private VerticalLayout caseDetailLayout;
    private SupportCase selectedCase;

    @Autowired
    public DashboardView(UserService userService) {
        this.userService = userService;
        initLayout();
    }

    private void initLayout() {
        Sidebar sidebar = new Sidebar();
        VerticalLayout mainContent = new VerticalLayout();
        add(sidebar, mainContent);


        caseGrid = new Grid<>(SupportCase.class);
        List<SupportCase> cases = userService.getAllCases();
        caseGrid.setItems(cases);
        caseGrid.asSingleSelect().addValueChangeListener(event -> showCaseDetails(event.getValue()));

        caseDetailLayout = new VerticalLayout();
        mainContent.add(caseGrid, caseDetailLayout);

        Button addCaseButton = new Button("Add Case", event -> showAddCaseForm());
        mainContent.add(addCaseButton);
    }

    private void showAddCaseForm() {
        caseDetailLayout.removeAll();

        TextField titleField = new TextField("Title");
        TextArea descriptionArea = new TextArea("Description");

        Button saveButton = new Button("Save", event -> {
            SupportCase newCase = new SupportCase();
            newCase.setTitle(titleField.getValue());
            newCase.setDescription(descriptionArea.getValue());
            // You may set other fields of SupportCase here as needed

            userService.saveSupportCase(newCase);
            Notification.show("Case added successfully");
            updateCaseGrid();
        });

        Button cancelButton = new Button("Cancel", event -> {
            caseDetailLayout.removeAll();
            showCaseDetails(selectedCase); // Show the previously selected case details again
        });

        caseDetailLayout.add(titleField, descriptionArea, saveButton, cancelButton);
    }

    private void showCaseDetails(SupportCase supportCase) {
        selectedCase = supportCase;
        caseDetailLayout.removeAll();

        TextField titleField = new TextField("Title", supportCase.getTitle());
        TextArea descriptionArea = new TextArea("Description", supportCase.getDescription());
        descriptionArea.setReadOnly(true);

        Grid<Comment> commentGrid = new Grid<>(Comment.class);
        List<Comment> comments = userService.getCommentsByCaseId(supportCase.getId());
        commentGrid.setItems(comments);

        TextArea commentField = new TextArea("Add Comment");
        Button addCommentButton = new Button("Add Comment", event -> {
            Comment comment = new Comment();
            comment.setContent(commentField.getValue());
            comment.setCreatedAt(LocalDateTime.now());
            comment.setSupportCase(selectedCase);
            comment.setUser(userService.getAuthenticatedUser());
            userService.saveComment(comment);
            comments.add(comment);
            commentGrid.getDataProvider().refreshAll();
            Notification.show("Comment added successfully");
        });

        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("image/jpeg", "image/png", "application/pdf");
        upload.setMaxFiles(1);

        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            StreamResource streamResource = new StreamResource(fileName, () -> buffer.getInputStream(fileName));
            // Process the uploaded file
            Notification.show("File uploaded: " + fileName);
        });

        Button viewAttachmentsButton = new Button("View Attachments", event -> {
            // Implement logic to display attachments associated with the selected case
            Notification.show("View Attachments clicked");
        });

        caseDetailLayout.add(
                titleField,
                descriptionArea,
                commentGrid,
                commentField,
                addCommentButton,
                upload,
                viewAttachmentsButton
        );
    }

    private void updateCaseGrid() {
        caseGrid.setItems(userService.getAllCases());
    }
}
