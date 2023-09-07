package tiktok.hackathon.controller;

import java.util.List;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tiktok.hackathon.exception.TransactionNotFoundException;
import tiktok.hackathon.model.Transaction;
import tiktok.hackathon.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
  private final TransactionService service;

  @Autowired
  public TransactionController(final @NonNull TransactionService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Void> add(@RequestBody Transaction transaction) {
    try {
      this.service.add(
          transaction.getCardId(), transaction.getAmount(), transaction.getTransactionDateTime());

      return ResponseEntity.ok().build();
    } catch (RuntimeException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/card/{cardId}")
  public ResponseEntity<List<Transaction>> retrieveTransactionsByCard(@PathVariable String cardId) {
    try {
      return ResponseEntity.ok(this.service.retrieveTransactionsByCard(cardId));
    } catch (RuntimeException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/{transactionId}")
  public ResponseEntity<Transaction> retrieveTransactionById(@PathVariable String transactionId) {
    try {
      return ResponseEntity.ok(this.service.findById(transactionId));
    } catch (TransactionNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
