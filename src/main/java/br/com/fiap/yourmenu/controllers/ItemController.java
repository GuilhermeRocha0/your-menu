package br.com.fiap.yourmenu.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.yourmenu.exception.RestNotFoundException;
import br.com.fiap.yourmenu.models.Category;
import br.com.fiap.yourmenu.models.Item;
import br.com.fiap.yourmenu.repositories.CategoryRepository;
import br.com.fiap.yourmenu.repositories.ItemRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ItemController {

    Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PagedResourcesAssembler assembler;

    @GetMapping("items")
    public PagedModel<EntityModel<Item>> showAllItems(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 3) Pageable pageable) {

        var items = search == null
                ? itemRepository.findAll(pageable)
                : itemRepository.findByNameContaining(search, pageable);

        return assembler.toModel(items.map(Item::toEntityModel));
    }

    @GetMapping("categories/{categoryId}/items")
    public PagedModel<EntityModel<List<Item>>> showItemsByCategory(
            @PathVariable Long categoryId,
            @PageableDefault(size = 3) Pageable pageable) {
        log.info("Buscando itens da categoria: " + categoryId);

        getCategory(categoryId);

        var items = itemRepository.findItemsByCategoryId(categoryId, pageable);

        return assembler.toModel(items.map(Item::toEntityModel));
    }

    @GetMapping("categories/{categoryId}/items/{id}")
    public ResponseEntity<Item> showItemById(
            @PathVariable Long categoryId,
            @PathVariable Long id) {
        log.info("Buscando categoria: " + id);

        getCategory(categoryId);
        var item = getItem(id);

        return ResponseEntity.ok(item);
    }

    @PutMapping("categories/{categoryId}/items")
    public ResponseEntity<EntityModel<Item>> createItem(@PathVariable Long categoryId,
            @RequestBody @Valid Item item) {

        log.info("Criando item na categoria " + categoryId);

        var category = getCategory(categoryId);
        category.items.add(item);
        item.setCategory(category);
        itemRepository.save(item);

        return ResponseEntity
                .created(item.toEntityModel().getRequiredLink("self").toUri())
                .body(item.toEntityModel());
    }

    @DeleteMapping("categories/{categoryId}/items/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable Long categoryId, @PathVariable Long id) {
        log.info("Apagando categoria: " + id);

        getCategory(categoryId);
        var item = getItem(id);
        itemRepository.delete(item);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("categories/{categoryId}/items/{id}")
    public ResponseEntity<EntityModel<Item>> updateItem(@PathVariable Long id,
            @RequestBody @Valid Item item) {

        log.info("Criando item na categoria: " + id);

        var itemFound = getItem(id);

        var categoryId = itemFound.getCategory().id;
        var category = getCategory(categoryId);

        item.setId(id);
        item.setCategory(category);
        itemRepository.save(item);

        return ResponseEntity.ok(item.toEntityModel());
    }

    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RestNotFoundException("despesa não encontrada"));
    }

    private Item getItem(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RestNotFoundException("item não encontrado"));
    }

}
