package com.suhane.food.repository;

import com.suhane.food.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Integer> {

    @Query(value = "select * from item i where i.item_code =?1 and i.name =?2 and i.vendor =?3",nativeQuery = true)
    ItemEntity findOrderByItemNameAndCodeAndVendor(String code, String name ,String vendor);
}
