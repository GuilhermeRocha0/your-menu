package br.com.fiap.yourmenu.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.yourmenu.models.Item;
import br.com.fiap.yourmenu.repositories.CategoryRepository;
import br.com.fiap.yourmenu.repositories.ItemRepository;

@RestController
@RequestMapping("/api/v1")
public class ItemController {

    Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("items")
    public ResponseEntity<List<Item>> showAllItems() {
        List<Item> items = itemRepository.findAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("categories/{categoryId}/items")
    public ResponseEntity<List<Item>> showItemsByCategory(@PathVariable Long categoryId) {
        log.info("Buscando itens da categoria: " + categoryId);

        var categoryFound = categoryRepository.findById(categoryId);

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        List<Item> items = categoryFound.get().getItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("categories/{categoryId}/items/{id}")
    public ResponseEntity<Item> showItemById(@PathVariable Long id) {
        log.info("Buscando categoria: " + id);

        var itemFound = itemRepository.findById(id);

        if (itemFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(itemFound.get());
    }

    @PutMapping("categories/{categoryId}/items")
    public ResponseEntity<Item> createItem(@PathVariable Long categoryId,
            @RequestBody Item item) {

        log.info("Criando item na categoria " + categoryId);

        var categoryFound = categoryRepository.findById(categoryId);
        log.info("Categoria: " + categoryFound.get());

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        categoryFound.get().items.add(item);
        item.setCategory(categoryFound.get());
        itemRepository.save(item);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("categories/{categoryId}/items/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable Long categoryId, @PathVariable Long id) {
        log.info("Apagando categoria: " + id);

        var categoryFound = categoryRepository.findById(categoryId);

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var itemFound = itemRepository.findById(id);

        if (itemFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        itemRepository.delete(itemFound.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("categories/{categoryId}/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id,
            @RequestBody Item item) {

        log.info("Criando item na categoria: " + id);

        var itemFound = itemRepository.findById(id);

        if (itemFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var categoryId = itemFound.get().getCategory().id;
        var categoryFound = categoryRepository.findById(categoryId);

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        item.setId(id);
        item.setCategory(categoryFound.get());
        itemRepository.save(item);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // // Items

    // @GetMapping("{id}/items")
    // public ResponseEntity<List<Item>> showItems(@PathVariable Long id) {
    // log.info("Buscando itens da categoria: " + id);

    // var categoryFound = categoryRepository.findById(id);

    // if (categoryFound.isEmpty())
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    // var category = categoryFound.get();
    // List<Item> items = itemRepository.findAll();
    // // List<Item> items = itemRepository.findAllByCategoryId(id);

    // return ResponseEntity.ok(items);
    // }

    // @PutMapping("{id}/items")
    // public ResponseEntity<Category> createItem(@PathVariable Long id,
    // @RequestBody Item item) {

    // log.info("Criando item na categoria: " + id);

    // var categoryFound = categoryRepository.findById(id);

    // if (categoryFound.isEmpty())
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    // // var category = categoryFound.get();
    // // categories.remove(categoryFound.get());

    // // item.setId(category.items.size() + 1L);
    // // category.setId(id);
    // // category.items.add(item);

    // // categories.add(category);

    // itemRepository.save(item);

    // return ResponseEntity.status(HttpStatus.CREATED).build();
    // }

    // @DeleteMapping("{id}/items/{itemId}")
    // public ResponseEntity<Item> deleteItem(@PathVariable Long id, @PathVariable
    // Long itemId) {
    // log.info("Apagando categoria: " + id);

    // var categoryFound = categories.stream().filter(c ->
    // c.getId().equals(id)).findFirst();

    // if (categoryFound.isEmpty())
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    // var category = categoryFound.get();
    // var itemFound = category.items.stream().filter(i ->
    // i.getId().equals(itemId)).findFirst();

    // if (itemFound.isEmpty())
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    // category.items.remove(itemFound.get());

    // return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    // }

    // @PutMapping("{id}/items/{itemId}")
    // public ResponseEntity<Category> updateItem(@PathVariable Long id,
    // @PathVariable Long itemId,
    // @RequestBody Item item) {

    // log.info("Criando item na categoria: " + id);

    // var categoryFound = categories.stream().filter(c ->
    // c.getId().equals(id)).findFirst();

    // if (categoryFound.isEmpty())
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    // var category = categoryFound.get();
    // categories.remove(categoryFound.get());

    // category.setId(id);

    // category.items.remove(item);
    // item.setId(itemId);
    // category.items.add(item);

    // categories.add(category);

    // return ResponseEntity.status(HttpStatus.CREATED).build();
    // }

}
