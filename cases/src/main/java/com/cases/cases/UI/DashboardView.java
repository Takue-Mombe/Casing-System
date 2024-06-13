package com.cases.cases.UI;

import com.cases.cases.Models.SupportCase;
import com.cases.cases.Services.SupportCaseService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("dashboard")
public class DashboardView extends VerticalLayout {

    @Autowired
    private SupportCaseService supportCaseService;

    public DashboardView() {
        Grid<SupportCase> grid = new Grid<>(SupportCase.class);
        grid.setItems(supportCaseService.findAll()); // Replace with actual method to get all cases

        add(grid);
    }
}

