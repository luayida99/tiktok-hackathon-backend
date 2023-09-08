package tiktok.hackathon.model;

import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TransactionFactory {
  public Transaction generate(String cardId, int amount, LocalDateTime transactionDateTime, String category, float lat, float lon, float merch_lat, float merch_lon, int age, String name, String number) {
    // null transactionId for Mongo to generate
    return new Transaction(null, cardId, amount, transactionDateTime, category, lat, lon, merch_lat, merch_lon, age, name, number);
  }
}
