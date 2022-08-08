package com.example.shop_spring_boot.service;

import com.example.shop_spring_boot.model.Item;
import com.example.shop_spring_boot.model.Seller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {
    List<Seller> sellerList = new ArrayList<>();

    public StoreService() {
        List<Item> itemList = new ArrayList<>();
        Item item1 = new Item(1, "phone", 5000);
        Item item2 = new Item(2, "notebook", 20000);
        itemList.add(item1);
        itemList.add(item2);
        Seller seller1 = new Seller("Bill", itemList);
        this.sellerList.add(seller1);
    }

    public List<Item> getItemsBySeller(String name) {
        for (Seller seller : this.sellerList) {
            if (name.toLowerCase().equals(seller.getName().toLowerCase())) {
                return seller.getItemList();
            }
        }
        return null;
    }

    public Item createItem(String name, Item item) {
        for (Seller seller : this.sellerList) {
            if (name.toLowerCase().equals(seller.getName().toLowerCase())) {
                seller.getItemList().add(item);
                return item;
            }
        }
        return null;
    }

    public Item updateItem(String name, int id, Item item) {
        for (Seller seller : this.sellerList) {
            if (name.toLowerCase().equals(seller.getName().toLowerCase())) {
                for (Item updatedItem : seller.getItemList()) {
                    if (id == updatedItem.getId()) {
                        updatedItem.setName(item.getName());
                        updatedItem.setPrice(item.getPrice());
                        return updatedItem;
                    }
                }
            }
        }
        return null;
    }

    public Item deleteItem(String name, int id) {
        for (Seller seller : this.sellerList) {
            if (name.toLowerCase().equals(seller.getName().toLowerCase())) {
                for (Item deletedItem : seller.getItemList()) {
                    if (id == deletedItem.getId()) {
                        seller.getItemList().remove(deletedItem);
                        return deletedItem;
                    }
                }
            }
        }
        return null;
    }
}
