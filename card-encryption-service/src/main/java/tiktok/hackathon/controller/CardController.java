package tiktok.hackathon.controller;

import java.util.List;
import java.util.logging.Logger;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tiktok.hackathon.model.Card;
import tiktok.hackathon.services.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {
  private final CardService cardService;
  private Logger logger = Logger.getLogger("");

  @Autowired
  public CardController(final @NonNull CardService cardService) {
    this.cardService = cardService;
  }

  @PostMapping
  public String save(@RequestBody Card card) {
    return this.cardService.save(
        card.getCardNumber(), card.getCvc(), card.getExpiryDate(), card.getUserId());
  }

  @GetMapping("/{userId}")
  public List<Card> retrieveAll(@PathVariable String userId) {
    System.out.println(userId);
    System.out.println("TEST");
    return this.cardService.retrieveAll(userId);
  }
}
