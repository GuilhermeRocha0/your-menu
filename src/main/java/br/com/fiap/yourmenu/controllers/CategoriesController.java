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
    public List<Categories> getCategories() {
        Categories category1 = new Categories(1l, "Main Dishes",
                Arrays.asList());
        Categories category2 = new Categories(2l, "Entrees",
                Arrays.asList());
        Categories[] categories = { category1, category2 };

        List<Categories> categoriesList = new ArrayList<>(Arrays.asList(categories));
        return categoriesList;
    }

    @GetMapping(value = "/categories/{categoryId}")
    public Categories getCategoriesById() {
        Categories category = new Categories(1l, "Main Dishes",
                Arrays.asList());
        Items item = new Items(1l, "Item 1", new BigDecimal(10), null, null, Arrays.asList(0, 1, 2));
        category.addItemToList(item);
        return category;
    }
}
