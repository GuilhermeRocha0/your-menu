package br.com.fiap.yourmenu.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.yourmenu.models.Category;
import br.com.fiap.yourmenu.models.Item;

@RestController
public class CategoryController {

    Logger log = LoggerFactory.getLogger(CategoryController.class);

    List<Category> categories = new ArrayList<>();

    @GetMapping("/api/v1/categories")
    public List<Category> showAllCategories() {
        log.info("categorias: " + categories);
        return categories;
    }

    @PostMapping("/api/v1/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        log.info("Cadastrando categorias: " + category);

        category.setId(categories.size() + 1L);
        category.items = new ArrayList<>();
        categories.add(category);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/v1/categories/{id}")
    public ResponseEntity<Category> showCategory(@PathVariable Long id) {
        log.info("Buscando categoria: " + id);

        var categoryFound = categories.stream().filter(c -> c.getId().equals(id)).findFirst();

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(categoryFound.get());
    }

    @DeleteMapping("/api/v1/categories/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        log.info("Apagando categoria: " + id);

        var categoryFound = categories.stream().filter(c -> c.getId().equals(id)).findFirst();

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        categories.remove(categoryFound.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/v1/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        log.info("Atualizando categoria" + id);

        var categoryFound = categories.stream().filter(c -> c.getId().equals(id)).findFirst();

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        categories.remove(categoryFound.get());
        category.setId(id);
        category.items = new ArrayList<>();
        categories.add(category);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Items

    @GetMapping("/api/v1/categories/{id}/items")
    public ResponseEntity<List<Item>> showItems(@PathVariable Long id) {
        log.info("Buscando itens da categoria: " + id);

        var categoryFound = categories.stream().filter(c -> c.getId().equals(id))
                .findFirst();

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var category = categoryFound.get();
        List<Item> items = new ArrayList<>();

        for (Item item : category.items) {
            items.add(item);
        }

        return ResponseEntity.ok(items);
    }

    @PutMapping("/api/v1/categories/{id}/items")
    public ResponseEntity<Category> createItem(@PathVariable Long id, @RequestBody Item item) {

        log.info("Criando item na categoria: " + id);

        var categoryFound = categories.stream().filter(c -> c.getId().equals(id)).findFirst();

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var category = categoryFound.get();
        categories.remove(categoryFound.get());

        item.setId(category.items.size() + 1L);
        category.setId(id);
        category.items.add(item);

        categories.add(category);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/api/v1/categories/{id}/items/{itemId}")
    public ResponseEntity<Item> deleteItem(@PathVariable Long id, @PathVariable Long itemId) {
        log.info("Apagando categoria: " + id);

        var categoryFound = categories.stream().filter(c -> c.getId().equals(id)).findFirst();

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var category = categoryFound.get();
        var itemFound = category.items.stream().filter(i -> i.getId().equals(itemId)).findFirst();

        if (itemFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        category.items.remove(itemFound.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/api/v1/categories/{id}/items/{itemId}")
    public ResponseEntity<Category> updateItem(@PathVariable Long id, @PathVariable Long itemId,
            @RequestBody Item item) {

        log.info("Criando item na categoria: " + id);

        var categoryFound = categories.stream().filter(c -> c.getId().equals(id)).findFirst();

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var category = categoryFound.get();
        categories.remove(categoryFound.get());

        category.setId(id);

        category.items.remove(item);
        item.setId(itemId);
        category.items.add(item);

        categories.add(category);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
