package br.com.fiap.yourmenu.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.yourmenu.models.Items;

@RestController
@RequestMapping("/api/v1/categories")
public class ItemsController {

    @GetMapping(value = "/1/items")
    public List<Object> getItemsByCategory() {
        Object[] items = {
                new Items(1l, "Item 1", new BigDecimal(10), null, null, Arrays.asList(0, 1, 2)),
                new Items(1l, "Item 2", new BigDecimal(20), null, null, Arrays.asList(0, 1, 2, 3))
        };
        List<Object> itemsList = new ArrayList<>(Arrays.asList(items));
        return itemsList;
    }

    @GetMapping(value = "/1/items/1")
    public Items getItemByCategoryAndId() {
        return new Items(1l, "Item 1", new BigDecimal(10), null, null, Arrays.asList(0, 1, 2));
    }
}
