package com.assignments.potluck;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Long> {
    Item findByItemType(String itemName);

}
