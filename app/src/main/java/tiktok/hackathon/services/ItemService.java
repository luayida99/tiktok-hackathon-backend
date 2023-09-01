package tiktok.hackathon.services;

import tiktok.hackathon.model.Item;

public interface ItemService {
    String save(Item item);

    String findByName(String name);
}
