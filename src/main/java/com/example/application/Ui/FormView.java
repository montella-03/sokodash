package com.example.application.Ui;

import com.example.application.Backend.entity.Product;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class FormView extends FormLayout {
    private Product product;
    private TextField productName = new TextField("productName");
    private TextField quantity = new TextField("quantity");
    private TextField price = new TextField("price");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    Binder<Product> productBinder = new BeanValidationBinder<>(Product.class);
    public FormView() {
        addClassName("formView");
        productBinder.bindInstanceFields(this);
        add(productName,
                quantity,
                price,
                createButtonsLayout());
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, product)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
        return new HorizontalLayout(save, delete, close);


    }

    private void validateAndSave() {
        try {
            productBinder.writeBean(product);
            fireEvent(new SaveEvent(this, product));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setProduct(Product product){
        this.product=product;
        productBinder.readBean(product);
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
 abstract class ProductFormEvent extends ComponentEvent<FormView> {
    private Product product;

    protected ProductFormEvent(FormView source, Product product) {
        super(source, false);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
class SaveEvent extends ProductFormEvent {
    SaveEvent(FormView source, Product product) {
        super(source, product);
    }
}
class DeleteEvent extends ProductFormEvent {
    DeleteEvent(FormView source, Product product) {
        super(source, product);
    }
}
class CloseEvent extends ProductFormEvent {
    CloseEvent(FormView source) {
        super(source, null);
    }
}

