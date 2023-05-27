package com.example.application.Ui;

import com.example.application.Backend.entity.Product;
import com.example.application.Backend.service.StoreService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
private final StoreService storeService;

    Grid<Product> grid = new Grid<>(Product.class);
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
        grid.setColumns("id","productName", "quantity", "price");
        grid.addColumn(product ->"$  " + (int)((product.getQuantity() * product.getPrice())/currentUSD()))
                .setHeader("Expected Revenue");


    }

    private double currentUSD() {
        return storeService.currentUSD();
    }
}
