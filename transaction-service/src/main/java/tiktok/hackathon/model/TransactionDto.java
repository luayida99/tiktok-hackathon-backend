package tiktok.hackathon.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDto {
  private String cardId;
  private String amount;
  private String transactionDateTime;
  private String category;
  private String lat;
  private String lon;
  private String merch_lat;
  private String merch_lon;
  private String dob;
  private String name;
  private String number;
}
