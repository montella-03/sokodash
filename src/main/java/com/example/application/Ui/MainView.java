package com.example.application.Ui;

import com.example.application.Backend.model.ProductModel;
import com.example.application.Backend.service.StoreService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    private final StoreService storeService;
    Grid<ProductModel> grid = new Grid<>(ProductModel.class);
    public MainView(StoreService storeService) {
    this.storeService = storeService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(grid);

        updateList();
        

    }

    private void updateList() {
        grid.setItems(storeService.findAll());
    }

    private void configureGrid() {
        grid.addClassName("product-grid");
        grid.setSizeFull();
        grid.setColumns("productName", "quantity", "price");


    }
}
