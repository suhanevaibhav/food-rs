package com.suhane.food.service;

import com.suhane.food.dto.Item;
import com.suhane.food.entity.ItemEntity;
import com.suhane.food.exception.FoodServiceException;
import com.suhane.food.repository.ItemRepository;
import com.suhane.food.utility.FoodUtilty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl {

    private static final Logger log = LogManager.getLogger(ItemServiceImpl.class);
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private FoodUtilty utilty;

    public Item saveItem(Item item) {
        try {

            ItemEntity entity = utilty.convertDtoToEntity(item);
            ItemEntity resEntity = itemRepository.save(entity);
            log.info("save the item successfully : {}",resEntity);
            return utilty.covertEntityToDto(resEntity);
        } catch (Exception e) {
            log.info("Exception in ItemServiceImpl saveItem method Item request data is : {} Exception: {}"
                    ,item,e.getMessage());
            throw new FoodServiceException(e.getMessage());
        }
    }

    public List<Item> FindAllItem() {
        List<Item> itemList = new ArrayList<>();
        try {

            List<ItemEntity> itemEntities = itemRepository.findAll();
            for (ItemEntity entity : itemEntities) {
                Item item = utilty.covertEntityToDto(entity);
                itemList.add(item);
            }
            log.info("find the all item successfully : {}" , itemList);
            return itemList;
        } catch (Exception e) {
            log.info("Exception in ItemServiceImpl while fetching all item by FindAllItem method: {}"
                    ,e.getMessage());
            throw new FoodServiceException(e.getMessage());
        }
    }
}
