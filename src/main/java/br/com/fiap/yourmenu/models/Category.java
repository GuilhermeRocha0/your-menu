package br.com.fiap.yourmenu.models;

import java.util.ArrayList;
import java.util.List;

public class Category {

    public Long id;
    public String name;
    public List<Item> items;

    public Category() {
    }

    public Category(Long id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
