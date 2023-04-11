package br.com.fiap.yourmenu.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {

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

}
