//package com.cases.cases.UI;
//
//import com.cases.cases.Models.SupportCase;
//import com.cases.cases.Services.SupportCaseService;
//import com.cases.cases.Services.UserService;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextArea;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.router.Route;
//import com.vaadin.flow.router.RouterLink;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.LocalDateTime;
//
//@Route("create-case")
//public class CreateSupportCaseView extends VerticalLayout {
//
//    @Autowired
//    private SupportCaseService supportCaseService;
//
//    @Autowired
//    private UserService userService;
//
//    public CreateSupportCaseView() {
//        // Create sidebar
//        VerticalLayout sidebar = createSidebar();
//
//        TextField titleField = new TextField("Title");
//        TextArea descriptionField = new TextArea("Description");
//        Button createButton = new Button("Create Case", event -> {
//            SupportCase supportCase = new SupportCase();
//            supportCase.setTitle(titleField.getValue());
//            supportCase.setDescription(descriptionField.getValue());
//            supportCase.setUser(userService.findByUsername("currentLoggedInUser")); // Get current logged-in user
//            supportCase.setCreatedAt(LocalDateTime.now());
//            supportCaseService.save(supportCase);
//            Notification.show("Case created successfully");
//        });
//
//        add(sidebar,titleField, descriptionField, createButton);
//    }
//    private VerticalLayout createSidebar() {
//        VerticalLayout sidebar = new VerticalLayout();
//        sidebar.setWidth("200px");
//        sidebar.setPadding(false);
//
//
//        RouterLink dashboardLink = new RouterLink("Dashboard", DashboardView.class);
//        RouterLink otherViewLink = new RouterLink("Case View", CaseDetailView.class);
//        RouterLink SupportCaseLink = new RouterLink("Support Case View", CreateSupportCaseView.class);
//        RouterLink accountInfoLink = new RouterLink("Account Info View", AccountInfoView.class);
//        sidebar.add(dashboardLink, otherViewLink,SupportCaseLink,accountInfoLink);
//
//        return sidebar;
//    }
//}
