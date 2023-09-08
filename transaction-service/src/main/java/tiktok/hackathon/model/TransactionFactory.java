package tiktok.hackathon.model;

import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TransactionFactory {
  public Transaction generate(String cardId, int amount, LocalDateTime transactionDateTime) {
    // null transactionId for Mongo to generate
    return new Transaction(null, cardId, amount, transactionDateTime);
  }
}
