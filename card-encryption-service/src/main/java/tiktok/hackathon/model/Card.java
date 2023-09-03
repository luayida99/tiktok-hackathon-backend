package tiktok.hackathon.model;

import java.time.YearMonth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tiktok.hackathon.crypto.cipherable.Cipherable;

@Document("card")
@Getter
@Setter
@AllArgsConstructor
public class Card {
  @Id private String cardNumber;
  private String cvc;
  private YearMonth expiryDate;
  private String userId;

  @SneakyThrows
  public Card encrypt(Cipherable cipherable) {
    return new Card(
        cipherable.encrypt(this.getCardNumber()),
        cipherable.encrypt(this.getCvc()),
        this.getExpiryDate(),
        this.getUserId());
  }

  @SneakyThrows
  public Card decrypt(Cipherable cipherable) {
    return new Card(
        cipherable.decrypt(this.cardNumber),
        cipherable.decrypt(this.cvc),
        this.expiryDate,
        this.userId);
  }
}
