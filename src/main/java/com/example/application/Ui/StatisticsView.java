package com.example.application.Ui;

import com.example.application.Backend.service.StoreService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "statistics", layout = MainLayout.class)
@PermitAll
public class StatisticsView extends VerticalLayout {
    private final StoreService storeService;

    public StatisticsView(StoreService storeService) {
        this.storeService = storeService;
        addClassName("statistics-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(getProductsCount(),getTotalPriceChart());
    }

    private Component getProductsCount() {
        Span span = new Span(storeService.countProducts()+" Products");
        span.addClassNames("mt-5", "text-xl", "text-center");
        return span;
    }

    private Component getTotalPriceChart() {
        Chart chart = new Chart(ChartType.BUBBLE);
        chart.getConfiguration().setTitle("Total price of products");
        DataSeries dataSeries = new DataSeries();
        storeService.findAll().forEach(product -> {
            dataSeries.add(new DataSeriesItem(product.getProductName(),product.getPrice()*product.getQuantity()));
        });
        chart.getConfiguration().setSeries(dataSeries);
        return chart;

    }
}
