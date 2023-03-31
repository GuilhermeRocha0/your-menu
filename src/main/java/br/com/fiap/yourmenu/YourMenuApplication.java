package br.com.fiap.yourmenu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fiap.yourmenu.models.Category;
import br.com.fiap.yourmenu.models.Item;
import br.com.fiap.yourmenu.repositories.CategoryRepository;

@SpringBootApplication
public class YourMenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourMenuApplication.class, args);
	}

	// @Autowired
	// private CategoryRepository categoryRepository;

	// @Override
	// public void run(String... args) throws Exception {

	// Category category = new Category(null, "café da manha");

	// Item item1 = new Item("Torrada", new BigDecimal(10), "Torrada com queijo e
	// azeite", null,
	// Arrays.asList(0, 1, 2));
	// Item item2 = new Item("Pão de Queijo", new BigDecimal(10), "Pão de Queijo",
	// "https://images.unsplash.com/photo-1559141680-d0bd7bc5af84?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80",
	// Arrays.asList(0, 1, 2));
	// // Item item3 = new Item();

	// category.getItems().add(item1);
	// category.getItems().add(item2);
	// // category.getItems().add(item3);

	// this.categoryRepository.save(category);

	// }
}
