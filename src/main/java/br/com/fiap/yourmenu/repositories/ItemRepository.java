package br.com.fiap.yourmenu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.yourmenu.models.Item;
import jakarta.transaction.Transactional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findCategoryById(Long categoryId);

    @Transactional
    void deleteByCategoryId(Long categoryId);
}
