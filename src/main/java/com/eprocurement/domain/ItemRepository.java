package com.eprocurement.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long>{

	Page<Item> findByDescriptionStartingWith(String description, Pageable pageable);
	Page<Item> findByNameStartingWith(String name, Pageable pageable);
}
