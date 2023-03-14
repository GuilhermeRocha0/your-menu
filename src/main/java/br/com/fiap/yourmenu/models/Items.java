package br.com.fiap.yourmenu.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Items {

    public Long id;
    public String name;
    public BigDecimal price;
    public String description;
    public String image;
    public List<Integer> daysOfTheWeek = new ArrayList<Integer>();

    public Items(Long id, String name, BigDecimal price, String description, String image,
            List<Integer> daysOfTheWeek) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.daysOfTheWeek = daysOfTheWeek;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Integer> getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public void setDaysOfTheWeek(List<Integer> daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }

}
