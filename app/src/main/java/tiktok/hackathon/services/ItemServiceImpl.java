package tiktok.hackathon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiktok.hackathon.model.Item;
import tiktok.hackathon.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public String save(Item item) {
        return itemRepository.save(item).getId();
    }

    @Override
    public String findByName(String name) {
        return itemRepository.findItemByName(name).getName();
    }
}
