package com.cases.cases.UI;

import com.cases.cases.Models.Attachment;
import com.cases.cases.Models.Comment;
import com.cases.cases.Services.AttachmentService;
import com.cases.cases.Services.CommentService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDateTime;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Route("case-detail")
public class CaseDetailView extends VerticalLayout {

    @Autowired
    private CommentService commentService;

    @Autowired
    private AttachmentService attachmentService;

    public CaseDetailView() {
        TextArea commentField = new TextArea("Comment");
        Button addCommentButton = new Button("Add Comment", event -> {
            Comment comment = new Comment();
            comment.setContent(commentField.getValue());
            comment.setCreatedAt(LocalDateTime.now());
            // Set the support case and user
            commentService.save(comment);
            Notification.show("Comment added successfully");
        });

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.addSucceededListener(event -> {
            InputStream inputStream = buffer.getInputStream();
            byte[] fileData = null;
            try {
                fileData = inputStream.readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Attachment attachment = new Attachment();
            attachment.setFileName(event.getFileName());
            attachment.setFileData(fileData);
            // Set the support case
            attachmentService.save(attachment);
            Notification.show("Attachment uploaded successfully");
        });

        add(commentField, addCommentButton, upload);
    }
}

