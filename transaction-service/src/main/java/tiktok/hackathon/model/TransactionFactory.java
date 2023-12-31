package tiktok.hackathon.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class TransactionFactory {
  public Transaction generate(
      String cardId,
      float amount,
      LocalDateTime transactionDateTime,
      String category,
      float lat,
      float lon,
      float merch_lat,
      float merch_lon,
      LocalDate dateOfBirth,
      String name,
      String number) {
    // null transactionId for Mongo to generate
    return new Transaction(
        null,
        cardId,
        amount,
        transactionDateTime,
        category,
        lat,
        lon,
        merch_lat,
        merch_lon,
        dateOfBirth,
        name,
        number,
        null);
  }
}
