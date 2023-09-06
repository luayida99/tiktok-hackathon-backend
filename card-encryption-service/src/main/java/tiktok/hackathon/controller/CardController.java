package tiktok.hackathon.controller;

import java.util.List;
import java.util.logging.Logger;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Void> save(@RequestBody Card card) {
    try {
      this.cardService.save(
          card.getCardNumber(),
          card.getCvc(),
          card.getExpiryYear(),
          card.getExpiryMonth(),
          card.getUserId(),
          card.getBank());
      return ResponseEntity.ok().build();
    } catch (RuntimeException ex) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("user/{userId}")
  public ResponseEntity<List<CardView>> retrieveAll(@PathVariable String userId) {
    try {
      return ResponseEntity.ok(this.cardService.retrieveAll(userId));
    } catch (RuntimeException ex) {
      return ResponseEntity.noContent().build();
    }
  }

  @GetMapping("card/{cardId}")
  public ResponseEntity<CardView> retrieveCard(@PathVariable String cardId) {
    try {
      return ResponseEntity.ok(this.cardService.findById(cardId));
    } catch (RuntimeException ex) {
      return ResponseEntity.notFound().build();
    }
  }
}
