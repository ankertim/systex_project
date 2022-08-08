package com.example.shop_spring_boot.controller;

import com.example.shop_spring_boot.model.Item;
import com.example.shop_spring_boot.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/store")
public class StoreController {
    @Autowired
    StoreService storeService;

    @GetMapping("/{name}")
    public List<Item> getItemsBySeller(@PathVariable String name) {
        List<Item> itemList = this.storeService.getItemsBySeller(name);
        return itemList;
    }

    @PostMapping("/{name}")
    public Item createItem(@PathVariable String name, @RequestBody Item item) {
        Item createdItem = this.storeService.createItem(name, item);
        return createdItem;
    }

    @PutMapping("/{name}/{id}")
    public Item updateItem(@PathVariable String name, @PathVariable int id, @RequestBody Item item) {
        Item updatedItem = this.storeService.updateItem(name, id, item);
        return updatedItem;
    }

    @DeleteMapping("/{name}/{id}")
    public Item deleteItem(@PathVariable String name, @PathVariable int id) {
        Item deletedItem = this.storeService.deleteItem(name, id);
        return deletedItem;
    }
}
