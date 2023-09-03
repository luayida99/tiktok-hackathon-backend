package tiktok.hackathon.model;

import java.time.YearMonth;
import javax.crypto.SecretKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("card")
@Getter
@Setter
@AllArgsConstructor
public class Card {
  @Id private String cardNumber;
  private String cvc;
  private YearMonth expiryDate;
  private String userId;
  private SecretKey secretKey;
}
