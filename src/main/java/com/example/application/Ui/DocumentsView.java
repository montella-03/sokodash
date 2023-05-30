package com.example.application.Ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "documents", layout = MainLayout.class)
@PermitAll
public class DocumentsView extends VerticalLayout {
    public DocumentsView() {
    }
}
