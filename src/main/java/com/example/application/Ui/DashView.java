package com.example.application.Ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "dash", layout = MainLayout.class)
@PermitAll
public class DashView extends VerticalLayout {
    public DashView() {
    }
}
