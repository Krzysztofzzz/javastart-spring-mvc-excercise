package com.javastart.order;

import com.javastart.common.Message;
import com.javastart.item.Item;
import com.javastart.item.ItemReposiotory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class OrderController {

    private ClientOrder clientOrder;
    private ItemReposiotory itemReposiotory;
    private OrderRepository orderRepository;

    public OrderController(ClientOrder clientOrder, ItemReposiotory itemReposiotory, OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.clientOrder = clientOrder;
        this.itemReposiotory = itemReposiotory;
    }

    @GetMapping("/zamowienie/dodaj")
    public String addItemToOrder(@RequestParam Long itemId, Model model) {
        Optional<Item> item = itemReposiotory.findById(itemId);
        item.ifPresent(clientOrder::add);
        if (item.isPresent()) {
            model.addAttribute("message", new Message("Dodano", "Do zamówienia dodano: " + item.get().getName()));
        } else {
            model.addAttribute("message", new Message("Nie dodano", "Pobrano błędne id z menu: " + itemId));
        }
        return "message";
    }

    @GetMapping("/zamowienie")
    public String getCurrentOrder(Model model) {
        model.addAttribute("order", clientOrder.getOrder());
        model.addAttribute("sum", clientOrder.getOrder().
                getItems().stream().
                mapToDouble(Item::getPrice).
                sum());
        return "order";
    }

    @PostMapping("/zamowienie/realizuj")
    public String proceedOrder(@RequestParam String address, @RequestParam String telephone, Model model){
        Order order = clientOrder.getOrder();
        order.setAddress(address);
        order.setTelephone(telephone);
        orderRepository.save(order);
        clientOrder.clear();
        model.addAttribute("message", new Message("Dziękujemy", "Zamówienie przekazane do realizacji"));
        return "message";
    }
}
