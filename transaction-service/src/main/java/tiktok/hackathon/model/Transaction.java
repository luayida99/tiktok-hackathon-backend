package tiktok.hackathon.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("transactions")
@Getter
@Setter
@AllArgsConstructor
public class Transaction {
  @Id private String transactionId;
  private String cardId;
  private int amount;
  private Date transactionDateTime;
  // TODO: AI model fields
}
