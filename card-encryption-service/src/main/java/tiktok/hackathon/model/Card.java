package tiktok.hackathon.model;

import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("card")
@Getter
@Setter
@AllArgsConstructor
public class Card {
  @Id private String id;

  @Indexed(unique = true)
  private String cardNumber;

  private String cvc;
  private int expiryYear;
  private int expiryMonth;
  private String userId;
  private String bank;
  private String secretKeyString;

  public SecretKey stringToKey() {
    byte[] keyBytes = Base64.getDecoder().decode(this.secretKeyString);
    return new SecretKeySpec(keyBytes, "AES");
  }

  public CardView getView() {
    return new CardView(
        this.id, this.cardNumber, this.cvc, this.expiryYear, this.expiryMonth, this.bank);
  }
}
