package br.com.fiap.yourmenu.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.fiap.yourmenu.controllers.CategoryController;
import br.com.fiap.yourmenu.controllers.ItemController;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category extends EntityModel<Category> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    @Size(min = 5, max = 255, message = "deve ser um nome significativo")
    public String name;

    @JsonIgnoreProperties("category")
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Item> items = new ArrayList<>();

    public Category(@NotBlank @Size(min = 5, max = 255, message = "deve ser um nome significativo") String name) {
        this.name = name;
    }

    public EntityModel<Category> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(CategoryController.class).showCategoryById(id)).withSelfRel(),
                linkTo(methodOn(CategoryController.class).deleteCategory(id)).withRel("delete"),
                linkTo(methodOn(CategoryController.class).showAllCategories(null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(ItemController.class).showItemsByCategory(id, Pageable.unpaged())).withRel("items"));
    }

}
