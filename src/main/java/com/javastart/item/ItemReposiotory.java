package com.javastart.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemReposiotory extends JpaRepository<Item, Long> {
}
