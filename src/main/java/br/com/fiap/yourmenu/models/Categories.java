package br.com.fiap.yourmenu.models;

import java.util.ArrayList;
// import java.util.ArrayList;
// import java.util.List;
import java.util.List;

public class Categories {

    public Long id;
    public String name;
    public List<Items> items;

    public Categories() {
    }

    public Categories(Long id, String name, List<Items> items) {
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

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public void addItemToList(Items item) {
        items.add(item);
    }

}
