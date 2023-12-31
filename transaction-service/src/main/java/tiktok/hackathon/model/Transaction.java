package tiktok.hackathon.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tiktok.hackathon.ai.risk.Risk;

@Document("transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
  @Id private String transactionId;
  private String cardId;
  private float amount;
  private LocalDateTime transactionDateTime;
  private String category;
  private float lat;
  private float lon;
  private float merch_lat;
  private float merch_lon;
  private LocalDate dateOfBirth;
  private String name;
  private String number;
  private Risk risk;
}
