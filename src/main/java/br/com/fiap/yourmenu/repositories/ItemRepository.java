package br.com.fiap.yourmenu.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.yourmenu.models.Item;
import jakarta.transaction.Transactional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findItemsByCategoryId(Long categoryId, Pageable pageable);

    Page<Item> findByNameContaining(String name, Pageable pageable);

}
