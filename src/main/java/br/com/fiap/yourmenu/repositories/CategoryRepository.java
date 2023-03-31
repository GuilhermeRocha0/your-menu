package br.com.fiap.yourmenu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.yourmenu.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
