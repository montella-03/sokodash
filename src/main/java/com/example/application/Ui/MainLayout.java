package com.example.application.Ui;

import com.example.application.Backend.security.SecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
        addToDrawer(new VerticalLayout(createDash(),createOrders(),createProduct(),
                createCustomers(),createDocuments(),createTasks(),
                createChats(),createStatistics()));

    }

    private HorizontalLayout createChats() {
        RouterLink chatsView = new RouterLink("CHATS", Chats.class);
        chatsView.setHighlightCondition(HighlightConditions.sameLocation());
        chatsView.addClassNames("font-italic");
        Icon icon = new Icon(VaadinIcon.CHAT);
        icon.addClassNames("mr-2","text-green-500");
        return new HorizontalLayout(icon,chatsView);
    }

    private HorizontalLayout createStatistics() {
        RouterLink stats = new RouterLink("ANALYTICS", StatisticsView.class);
        stats.setHighlightCondition(HighlightConditions.sameLocation());
        stats.addClassNames("font-italic");
        Icon icon = new Icon(VaadinIcon.CHART);
        icon.addClassNames("mr-2","text-green-500");
        return new HorizontalLayout(icon,stats);
    }

    private HorizontalLayout createTasks() {
        RouterLink tasksView = new RouterLink("TASKS", TaskView.class);
        tasksView.setHighlightCondition(HighlightConditions.sameLocation());
        tasksView.addClassNames("font-italic");
        Icon icon = new Icon(VaadinIcon.TASKS);
        icon.addClassNames("mr-2","text-green-500");
        return new HorizontalLayout(icon,tasksView);
    }

    private HorizontalLayout createDocuments() {
        RouterLink documentsView = new RouterLink("DOCUMENTS", DocumentsView.class);
        documentsView.setHighlightCondition(HighlightConditions.sameLocation());
        documentsView.addClassNames("font-italic");
        Icon icon = new Icon(VaadinIcon.FILE_TEXT);
        icon.addClassNames("mr-2","text-green");
        return new HorizontalLayout(icon,documentsView);
    }

    private HorizontalLayout createCustomers() {
        RouterLink view = new RouterLink("CUSTOMERS", CustomersView.class);
        view.setHighlightCondition(HighlightConditions.sameLocation());
        view.addClassNames("font-italic");
        Icon icon = new Icon(VaadinIcon.USERS);
        icon.addClassNames("mr-2","text-green-500");
        return new HorizontalLayout(icon,view);
    }

    private HorizontalLayout createOrders() {
        RouterLink ordersView = new RouterLink("ORDERS", OrdersView.class);
        ordersView.setHighlightCondition(HighlightConditions.sameLocation());
        ordersView.addClassNames("font-italic");
        Icon icon = new Icon(VaadinIcon.CART);
        icon.addClassNames("mr-2","text-green-500");
        return new HorizontalLayout(icon,ordersView);
    }

    private HorizontalLayout createDash() {
        RouterLink dashView = new RouterLink("SOKOBOARD", DashView.class);
        dashView.setHighlightCondition(HighlightConditions.sameLocation());
        dashView.addClassNames("font-italic");
        Icon icon = new Icon(VaadinIcon.DASHBOARD);
        icon.addClassNames("mr-2","text-green-500");
        return new HorizontalLayout(icon,dashView);
    }

    private HorizontalLayout createProduct() {
        RouterLink mainView = new RouterLink("PRODUCTS", MainView.class);
        mainView.addClassNames("font-italic");
        mainView.setHighlightCondition(HighlightConditions.sameLocation());
        Icon icon = new Icon(VaadinIcon.CUBE);
        icon.addClassNames("mr-2","text-green-500");
        return new HorizontalLayout(icon,mainView);

    }
}
