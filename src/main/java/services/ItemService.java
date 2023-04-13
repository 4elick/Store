package services;

import entities.Item;
import repositories.ItemRepository;

import java.util.List;

public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void add(Item item){
        itemRepository.add(item);
    }

    public Item getById(long id){
        return itemRepository.getById(id);
    }

    public List<Item> getAll(){
        return itemRepository.getAll();
    }
    public void removeById(long id){
        itemRepository.removeById(id);
    }
}
