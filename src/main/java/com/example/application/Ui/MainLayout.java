package com.example.application.Ui;

import com.example.application.Backend.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("SOKODASH ADMIN");
        logo.addClassNames("text-xl", "font-bold", "text-gray-800", "p-2","m-m","font-mono");
        Image image = new Image("./images/empty-plant.png", "SOKODASH logo");
        image.setHeight("44px");
        image.setWidth("44px");
        image.addClassNames("rounded-full","m-2");
        Button logout = new Button("Logout", event -> {
            securityService.logout();
//            event.getSource().getUI().ifPresent(ui -> ui.navigate("login"));
        });
        logout.addClassNames("flex-end","mr-5");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(),image,logo,logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0","px-m");
        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink mainView = new RouterLink("PRODUCTS", MainView.class);
        mainView.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink view = new RouterLink("CUSTOMERS", CustomersView.class);
        view.addClassNames("mt-5","font-mono");
        view.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink stats = new RouterLink("Statistics", StatisticsView.class);
        stats.setHighlightCondition(HighlightConditions.sameLocation());
        stats.addClassNames("mt-5","font-mono");
        RouterLink orders = new RouterLink("Orders", OrdersView.class);
        orders.setHighlightCondition(HighlightConditions.sameLocation());
        orders.addClassNames("mt-5","font-mono");
        RouterLink about = new RouterLink("About", AboutView.class);
        about.setHighlightCondition(HighlightConditions.sameLocation());
        about.addClassNames("mt-5","font-mono");
        addToDrawer(new VerticalLayout(mainView,view,orders,stats,about));

    }
}
