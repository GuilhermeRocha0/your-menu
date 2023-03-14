package br.com.fiap.yourmenu.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.yourmenu.models.Categories;
import br.com.fiap.yourmenu.models.Items;

@RestController
@RequestMapping("/api/v1")
public class CategoriesController {

    @GetMapping(value = "/categories")
    public List<Object> getCategories() {
        Object[] categories = {
                new Categories(1l, "Main Dishes",
                        Arrays.asList(new Items(1l, "Item 1", new BigDecimal(10), null, null, Arrays.asList(0, 1, 2)))),
                new Categories(1l, "Entrees",
                        Arrays.asList(new Items(1l, "Item 1", new BigDecimal(10), null, null, Arrays.asList(0, 1, 2))))
        };
        List<Object> categoriesList = new ArrayList<>(Arrays.asList(categories));
        return categoriesList;
    }

    @GetMapping(value = "/categories/{categoryId}")
    public Categories getCategoriesById() {
        return new Categories(1l, "Main Dishes",
                Arrays.asList(new Items(1l, "Item 1", new BigDecimal(10), null, null, Arrays.asList(0, 1, 2))));
    }
}
