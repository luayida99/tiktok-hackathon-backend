package tiktok.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tiktok.hackathon.model.Item;
import tiktok.hackathon.services.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
  @Autowired private ItemService itemService;

  @GetMapping
  public String find(@RequestParam String name) {
    return itemService.findByName(name);
  }

  @PostMapping
  public String save(@RequestBody Item item) {
    return itemService.save(item);
  }
}
