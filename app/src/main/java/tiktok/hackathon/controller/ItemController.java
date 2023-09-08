package tiktok.hackathon.controller;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;
import tiktok.hackathon.model.Item;
import tiktok.hackathon.services.ItemService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/items")
public class ItemController {
  @Autowired private ItemService itemService;

  private Logger logger = Logger.getLogger("");

  @GetMapping
  public String find(@RequestParam String name) { // http://localhost:8080/items?name=cheese
    logger.info(name);
    return itemService.findByName(name);
  }

  @GetMapping("/test")
  public String test() {
    logger.info("testing");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String uid = authentication.getName();
    return uid;
  }

  @PostMapping
  public String save(@RequestBody Item item) {
    return itemService.save(item);
  }
}
