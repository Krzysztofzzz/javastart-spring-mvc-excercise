package com.javastart.controllers;

import com.javastart.item.Item;
import com.javastart.item.ItemReposiotory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ItemReposiotory itemReposiotory;

    public HomeController(ItemReposiotory itemReposiotory) {
        this.itemReposiotory = itemReposiotory;
    }

    @GetMapping("/")
    String home(Model model){
        List<Item> items = itemReposiotory.findAll();
        model.addAttribute("items", items);
        return "index";
    }
}
