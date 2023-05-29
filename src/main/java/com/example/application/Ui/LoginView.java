package com.example.application.Ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.Route;

@Route(value = "login")
public class LoginView extends VerticalLayout implements BeforeEnterListener {
    private LoginForm loginForm = new LoginForm();
    public LoginView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        loginForm.setAction("login");

        add(new H1("sokodash ltd"), loginForm);
    }
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters().
                getParameters()
                .containsKey("error")) {
            loginForm.setError(true);
        }

    }
}
