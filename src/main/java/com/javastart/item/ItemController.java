package com.javastart.item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class ItemController {
    private ItemReposiotory itemReposiotory;

    public ItemController(ItemReposiotory itemReposiotory) {
        this.itemReposiotory = itemReposiotory;
    }

    @GetMapping("/danie/{name}")
    public String getItem(@PathVariable String name, Model model){
        Optional<Item> item = itemReposiotory.findByNameIgnoreCase(name.replaceAll("-"," "));
        item.ifPresent(it -> model.addAttribute("item", it));
        return item.map(it -> "item").orElse("redirect:/");
    }
}
