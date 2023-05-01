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

}
