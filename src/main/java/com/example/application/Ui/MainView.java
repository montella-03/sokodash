package com.example.application.Ui;

import com.example.application.Backend.entity.Product;
import com.example.application.Backend.service.StoreService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
private final StoreService storeService;
    private Grid<Product> grid = new Grid<>(Product.class);
    private TextField filterText = new TextField();
    private FormView form;
    public MainView(StoreService storeService) {
        this.storeService = storeService;


        addClassName("list-view");
        setSizeFull();
        configureFilter();
        configureGrid();

        form = new FormView();
        Div content = new Div(grid,form);
        content.addClassName("content");
        content.setSizeFull();

        add(filterText,content);
        updateList();
        closeEditor();
        

    }

    private void closeEditor() {
        form.setProduct(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by product name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }

    private void updateList() {
        grid.setItems(storeService.findAll(filterText.getValue()));
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