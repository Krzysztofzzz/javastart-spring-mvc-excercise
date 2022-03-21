package com.javastart.order;

import com.javastart.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class ClientOrder {

    private Order order;

    public ClientOrder() {
        clear();
    }

    public Order getOrder() {
        return order;
    }

    void add(Item item){
        order.getItems().add(item);
    }

    void clear(){
        order = new Order();
        order.setStatus(OrderStatus.NEW);
    }
}
