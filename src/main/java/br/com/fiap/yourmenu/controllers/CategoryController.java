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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.yourmenu.exception.RestNotFoundException;
import br.com.fiap.yourmenu.models.Category;
import br.com.fiap.yourmenu.repositories.CategoryRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/categories")
@Slf4j
public class CategoryController {

    Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> showAllCategories(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 1) Pageable pageable) {
        var categories = search == null
                ? categoryRepository.findAll(pageable)
                : categoryRepository.findByNameContaining(search, pageable);

        return assembler.toModel(categories.map(Category::toEntityModel));
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> showCategoryById(@PathVariable Long id) {
        log.info("Buscando categoria: " + id);
        return ResponseEntity.ok(getCategory(id));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Category>> createCategory(
            @RequestBody @Valid Category category,
            BindingResult result) {
        log.info("Cadastrando categorias: " + category);
        categoryRepository.save(category);
        return ResponseEntity
                .created(category.toEntityModel().getRequiredLink("self").toUri())
                .body(category.toEntityModel());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        log.info("Apagando categoria: " + id);
        categoryRepository.delete(getCategory(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Category>> updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid Category category) {
        log.info("Atualizando categoria" + id);
        getCategory(id);
        category.setId(id);
        categoryRepository.save(category);
        return ResponseEntity.ok(category.toEntityModel());
    }

    private Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("despesa não encontrada"));
    }

}
