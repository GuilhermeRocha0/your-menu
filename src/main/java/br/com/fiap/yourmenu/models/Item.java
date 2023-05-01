package br.com.fiap.yourmenu.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.fiap.yourmenu.controllers.CategoryController;
import br.com.fiap.yourmenu.controllers.ItemController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "items")
public class Item extends EntityModel<Item> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    @Size(min = 5, max = 255, message = "deve ser um nome significativo")
    public String name;

    @NotNull
    @Min(value = 0, message = "deve ser um valor positivo")
    public BigDecimal price;

    @Size(min = 5, max = 255, message = "deve ser uma descrição significativa")
    public String description;

    public String image;
    public List<Integer> daysOfTheWeek = new ArrayList<Integer>();

    @JsonIgnoreProperties("items")
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public EntityModel<Item> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(ItemController.class).showItemById(this.getCategory().getId(), id)).withSelfRel(),
                linkTo(methodOn(ItemController.class).deleteItem(this.getCategory().getId(), id)).withRel("delete"),
                linkTo(methodOn(ItemController.class).showAllItems(null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(CategoryController.class).showCategoryById(this.getCategory().getId()))
                        .withRel("category"));
    }

}
