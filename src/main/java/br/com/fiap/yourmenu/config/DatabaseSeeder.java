package br.com.fiap.yourmenu.config;

import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fiap.yourmenu.models.Category;
import br.com.fiap.yourmenu.models.Item;
import br.com.fiap.yourmenu.repositories.CategoryRepository;
import br.com.fiap.yourmenu.repositories.ItemRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

        @Autowired
        CategoryRepository categoryRepository;

        @Autowired
        ItemRepository itemRepository;

        @Override
        @Profile("dev")
        public void run(String... args) throws Exception {

                Category c1 = new Category("Breakfast");
                Category c2 = new Category("Lunch");
                Category c3 = new Category("Dinner");
                categoryRepository.saveAll(List.of(c1, c2, c3));

                itemRepository.saveAll(List.of(
                                Item.builder().name("Breakfast Sandwich").price(new BigDecimal(20))
                                                .image("https://images.unsplash.com/photo-1608847569619-b5602f65ffa0?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=686&q=80")
                                                .daysOfTheWeek(List.of(0, 1, 2, 3, 4, 5, 6)).category(c1).build(),
                                Item.builder().name("Eggs Benedict").price(new BigDecimal(20))
                                                .image("https://images.unsplash.com/photo-1608039829572-78524f79c4c7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80")
                                                .daysOfTheWeek(List.of(0, 1, 2, 3, 4, 5, 6)).category(c1).build(),
                                Item.builder().name("French toast").price(new BigDecimal(20)).image(
                                                "https://images.unsplash.com/photo-1484723091739-30a097e8f929?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=749&q=80")
                                                .daysOfTheWeek(List.of(0, 1, 2, 3, 4, 5, 6)).category(c1).build(),
                                Item.builder().name("Chicken and waffles").price(new BigDecimal(20))
                                                .image("https://images.unsplash.com/photo-1600891963935-9e9544daf776?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171&q=80")
                                                .daysOfTheWeek(List.of(0, 1, 2, 3, 4, 5, 6)).category(c1).build()

                ));

        }

}
