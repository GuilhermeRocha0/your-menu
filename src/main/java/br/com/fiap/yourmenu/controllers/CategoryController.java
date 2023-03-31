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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.yourmenu.models.Category;
import br.com.fiap.yourmenu.repositories.CategoryRepository;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> showAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> showCategoryById(@PathVariable Long id) {
        log.info("Buscando categoria: " + id);

        var categoryFound = categoryRepository.findById(id);

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(categoryFound.get());
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        log.info("Cadastrando categorias: " + category);
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        log.info("Apagando categoria: " + id);

        var categoryFound = categoryRepository.findById(id);

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        categoryRepository.delete(categoryFound.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        log.info("Atualizando categoria" + id);

        var categoryFound = categoryRepository.findById(id);

        if (categoryFound.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        category.setId(id);
        categoryRepository.save(category);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
