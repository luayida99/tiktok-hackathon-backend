package tiktok.hackathon.controller;

import java.util.List;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
  public void add(@RequestBody Transaction transaction) {
    this.service.add(
        transaction.getCardId(), transaction.getAmount(), transaction.getTransactionDateTime());
  }

  @GetMapping("/card/{cardId}")
  public List<Transaction> retrieveTransactionsByCard(@PathVariable String cardId) {
    return this.service.retrieveTransactionsByCard(cardId);
  }

  @GetMapping("/{transactionId}")
  public Transaction retrieveTransactionById(@PathVariable String transactionId) {
    return this.service.findById(transactionId);
  }
}
