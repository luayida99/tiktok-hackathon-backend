package tiktok.hackathon.model;

import java.time.YearMonth;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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
  private String secretKeyString;

  public SecretKey stringToKey() {
    System.out.println(this.secretKeyString);
    byte[] keyBytes = Base64.getDecoder().decode(this.secretKeyString);
    return new SecretKeySpec(keyBytes, "AES");
  }
}
