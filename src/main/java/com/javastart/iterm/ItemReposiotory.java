package com.javastart.iterm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemReposiotory extends JpaRepository<Item, Long> {
}
