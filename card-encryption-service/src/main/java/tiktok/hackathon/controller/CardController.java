package tiktok.hackathon.controller;

import java.util.List;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tiktok.hackathon.model.Card;
import tiktok.hackathon.services.CardService;
import tiktok.hackathon.services.CardServiceImpl;

@RestController
@RequestMapping("/cards")
public class CardController {
  private final CardService cardService;

  @Autowired
  public CardController(final @NonNull CardServiceImpl cardService) {
    this.cardService = cardService;
  }

  @PostMapping
  public String save(@RequestBody Card card) {
    return this.cardService.save(card);
  }

  @GetMapping
  public List<Card> retrieveAll(@PathVariable String userId) {
    return this.cardService.retrieveAll(userId);
  }
}
