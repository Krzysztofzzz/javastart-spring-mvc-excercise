package com.javastart.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemReposiotory extends JpaRepository<Item, Long> {

    Optional<Item> findByNameIgnoreCase(String name);
}
