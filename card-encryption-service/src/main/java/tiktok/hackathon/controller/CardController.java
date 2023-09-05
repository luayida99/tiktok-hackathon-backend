package tiktok.hackathon.controller;

import java.util.List;
import java.util.logging.Logger;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tiktok.hackathon.model.Card;
import tiktok.hackathon.model.CardView;
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
  public void save(@RequestBody Card card) {
    this.cardService.save(
        card.getCardNumber(),
        card.getCvc(),
        card.getExpiryYear(),
        card.getExpiryMonth(),
        card.getUserId(),
        card.getBank());
  }

  @GetMapping("/{userId}")
  public List<CardView> retrieveAll(@PathVariable String userId) {
    return this.cardService.retrieveAll(userId);
  }
}
