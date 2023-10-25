package com.example.application.Ui;

import com.example.application.Backend.entity.Product;
import com.example.application.Backend.model.ProductModel;
import com.example.application.Backend.service.StoreService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "", layout = MainLayout.class)
@PermitAll
public class MainView extends VerticalLayout {
private final StoreService storeService;
    private final Grid<Product> grid = new Grid<>(Product.class);
    private final TextField filterText = new TextField();
    private FormView form;
    ConfirmDialog confirmDialog = new ConfirmDialog();
    public MainView(StoreService storeService) {
        this.storeService = storeService;


        addClassName("list-view");
        setSizeFull();
        configureGrid();//just columns and single select listener.
        configureForm();//add save,delete,close listener. addSaveListener(this::saveProduct)

        add(getToolbar(),getContent()); // filter add, grid and form
        updateList();
        closeEditor();
        

    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        var addProductButton = new Button("Add product");
        addProductButton.addClickListener(click -> addProduct());

        var toolbar = new HorizontalLayout(filterText, addProductButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addProduct() {
        grid.asSingleSelect().clear();
        editProduct(new Product());
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
        form = new FormView();
        form.setWidth("25em");
        form.addSaveListener( this::saveProduct);
        form.addDeleteListener(this::deleteProduct);
        form.addCloseListener( e -> closeEditor());
    }

    private void deleteProduct(FormView.DeleteEvent deleteEvent) {

        confirmDialog.setHeader("Confirm delete");
        confirmDialog.setText("Are you sure you want to delete this product");
        confirmDialog.open();
        confirmDialog.setConfirmButton("Delete", event -> {
            storeService.deleteProduct(deleteEvent.getProduct().getId());
            updateList();
            closeEditor();
            confirmDialog.close();
        });
        confirmDialog.setCancelButton("Cancel", event -> {
            confirmDialog.close();
        });
    }

    private void saveProduct(FormView.SaveEvent saveEvent) {
        ProductModel productModel = new ProductModel(
                saveEvent.getProduct().getProductName(),
                saveEvent.getProduct().getQuantity(),
                saveEvent.getProduct().getPrice()
        );
        storeService.addProduct(productModel);
        updateList();
        closeEditor();
        Notification notification = Notification
                .show("Product saved successfully");
        notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
        notification.setPosition(Notification.Position.TOP_CENTER);
    }


    private void closeEditor() {
        form.setProduct(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(storeService.search(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassName("product-grid");
        grid.setSizeFull();
        grid.setColumns("id","productName", "quantity", "price");
        grid.addColumn(product ->"$  " + (int)((product.getQuantity() * product.getPrice())/currentUSD()))
                .setHeader("Expected Revenue");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> editProduct(event.getValue()));


    }

    private void editProduct(Product value) {
        if (value == null) {
            closeEditor();
        } else {
            form.setProduct(value);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private double currentUSD() {
        return storeService.currentUSD();
    }
}
//create a method to collect the current usd exchange to ksh?