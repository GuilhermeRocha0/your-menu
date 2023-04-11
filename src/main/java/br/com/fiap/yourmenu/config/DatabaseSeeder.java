package br.com.fiap.yourmenu.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.yourmenu.models.Category;
import br.com.fiap.yourmenu.repositories.CategoryRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        categoryRepository.saveAll(List.of(
                new Category("Breakfast"),
                new Category("Lunch"),
                new Category("Dinner")));

    }

}
