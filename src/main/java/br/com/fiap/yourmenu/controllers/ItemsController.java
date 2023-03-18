package br.com.fiap.yourmenu.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.yourmenu.models.Categories;
import br.com.fiap.yourmenu.models.Items;

@RestController
@RequestMapping("/api/v1/categories")
public class ItemsController {

    @GetMapping(value = "/{categoryId}/items")
    public List<Items> getItemsByCategory() {
        Categories category = new Categories(1l, "Main Dishes",
                Arrays.asList());
        Items item = new Items(1l, "Item 1", new BigDecimal(10), null, null, Arrays.asList(0, 1, 2));
        category.addItemToList(item);
        return category.getItems();
    }

    @GetMapping(value = "/{categoryId}/items/{itemId}")
    public Items getItemByCategoryAndId() {
        return new Items(1l, "Item 1", new BigDecimal(10), null, null,
                Arrays.asList(0, 1, 2));
    }
}
