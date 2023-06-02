package com.example.application.Ui;

import com.example.application.Backend.entity.Customer;
import com.example.application.Backend.service.StoreService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Route(value = "chats", layout = MainLayout.class)
@PermitAll
public class Chats extends VerticalLayout {

    private final StoreService storeService;

    public Chats(StoreService storeService) {
        this.storeService = storeService;
        add(new H1("Chats"));
        MessageList list = new MessageList();
        MessageInput input = new MessageInput();
        input.addSubmitListener(submitEvent -> {
            MessageListItem newMessage = new MessageListItem(
                    submitEvent.getValue(), Instant.now(), storeService.getAll().get(2).getName());
            newMessage.setUserColorIndex(3);
            List<MessageListItem> items = new ArrayList<>(list.getItems());
            items.add(newMessage);
            list.setItems(items);
        });

        Customer person = storeService.getAll().get(0);
        Customer person1 = storeService.getAll().get(1);
        MessageListItem message1 = new MessageListItem(
                "Nature does not hurry, yet everything gets accomplished.",
                LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC),
                person1.getName());
        message1.setUserColorIndex(1);
        MessageListItem message2 = new MessageListItem(
                "Using your talent, hobby or profession in a way that makes you contribute with something good to this world is truly the way to go.",
                LocalDateTime.now().minusMinutes(55).toInstant(ZoneOffset.UTC),
                 person.getName());
        message2.setUserColorIndex(2);
        list.setItems(message1, message2);

        VerticalLayout chatLayout = new VerticalLayout(list, input);
        chatLayout.setHeight("500px");
        chatLayout.setWidth("400px");
        chatLayout.expand(list);
        add(chatLayout);
    }


    }

