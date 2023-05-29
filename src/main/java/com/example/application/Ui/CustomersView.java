package com.example.application.Ui;

import com.example.application.Backend.entity.Customer;
import com.example.application.Backend.model.CustomerModel;
import com.example.application.Backend.service.StoreService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "customers", layout = MainLayout.class)
@PageTitle("Customers")
@PermitAll
public class CustomersView extends VerticalLayout {
    private final StoreService storeService;
    private Grid<Customer> grid = new Grid<>(Customer.class);
    private TextField filterText = new TextField();
    private CustomerForm form;
    public CustomersView(StoreService storeService) {
        this.storeService = storeService;
        addClassName("customers");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(),getContent());
        updateList();
        closeEditor();


    }

    private Component getToolbar() {
        filterText.setPlaceholder("Search customer...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addCustomerButton = new Button("Add customer");
        addCustomerButton.addClickListener(click -> addCustomer());

        var toolbar = new HorizontalLayout(filterText, addCustomerButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addCustomer() {
        grid.asSingleSelect().clear();
        editCustomer(new Customer());
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.addClassName("content");
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new CustomerForm();
        form.setWidth("25em");
        form.addSaveListener( this::saveCustomer);
        form.addDeleteListener(this::deleteCustomer);
        form.addCloseListener( e -> closeEditor());
    }

    private void deleteCustomer(CustomerForm.DeleteEvent deleteEvent) {
        storeService.deleteCustomer(deleteEvent.getCustomer().getId());
        updateList();
        closeEditor();
    }

    private void saveCustomer(CustomerForm.SaveEvent saveEvent) {
        CustomerModel customerModel = new CustomerModel(
                saveEvent.getCustomer().getName(),
                saveEvent.getCustomer().getEmail(),
                saveEvent.getCustomer().getAddress()

        );
        storeService.addCustomer(customerModel);
        updateList();
        closeEditor();
    }


    private void closeEditor() {
        form.setCustomer(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(storeService.getAll(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassName("product-grid");
        grid.setSizeFull();
        grid.setColumns("id","name","email", "address");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> editCustomer(event.getValue()));


    }

    private void editCustomer(Customer value) {
        if (value == null) {
            closeEditor();
        } else {
            form.setCustomer(value);
            form.setVisible(true);
            addClassName("editing");
        }
    }

}


