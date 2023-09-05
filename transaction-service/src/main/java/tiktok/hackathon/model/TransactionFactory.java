package tiktok.hackathon.model;

import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TransactionFactory {
  public Transaction generate(String cardId, int amount, Date transactionDateTime) {
    // null transactionId for Mongo to generate
    return new Transaction(null, cardId, amount, transactionDateTime);
  }
}
