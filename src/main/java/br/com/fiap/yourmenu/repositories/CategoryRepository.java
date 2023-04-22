package br.com.fiap.yourmenu.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.yourmenu.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findByNameContaining(String name, Pageable pageable);

}
