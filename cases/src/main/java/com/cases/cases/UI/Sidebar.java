package com.cases.cases.UI;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Sidebar extends VerticalLayout {

    public Sidebar() {
        Anchor dashboardLink = new Anchor("/dashboard", "Dashboard");
        Anchor accountInfoLink = new Anchor("/account-info", "Account Info");

        add(dashboardLink, accountInfoLink);
    }
}

