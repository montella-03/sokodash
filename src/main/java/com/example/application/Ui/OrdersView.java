package com.example.application.Ui;

import com.example.application.Backend.entity.Orders;
import com.example.application.Backend.service.StoreService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "orders", layout = MainLayout.class)
@PermitAll
public class OrdersView extends VerticalLayout {
    private final StoreService storeService;
    Grid<Orders> ordersGrid = new Grid<>(Orders.class);
    TextField filterText = new TextField();

    public OrdersView(StoreService storeService) {
        this.storeService = storeService;
        addClassName("orders-view");
        setSizeFull();
        add(configureGrid());

    }

    private Component configureGrid() {
        ordersGrid.setClassName("orders-grid");
        ordersGrid.setSizeFull();
        ordersGrid.setColumns("orderId", "orderDate",
                "amount");
        ordersGrid.addColumn(orders -> orders.getProducts().getProductName())
                .setHeader("Product");
        ordersGrid.addColumn(orders -> storeService.getCustomer(orders.getCustomerId()))
                .setHeader("Customer");
        ordersGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        ordersGrid.setItems(storeService.orders());
        return ordersGrid;
    }


}
