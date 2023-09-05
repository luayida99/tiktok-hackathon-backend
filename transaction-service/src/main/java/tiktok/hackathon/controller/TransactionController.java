package tiktok.hackathon.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
  public void add(Transaction transaction) {
    this.service.add(transaction);
  }
}
