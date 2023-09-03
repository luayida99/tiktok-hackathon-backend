package tiktok.hackathon.model;

import java.time.YearMonth;
import javax.crypto.KeyGenerator;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tiktok.hackathon.crypto.cipherable.Cipherable;

@Component
public class CardFactory {
  private KeyGenerator keyGenerator;

  @Autowired
  public CardFactory(final @NonNull KeyGenerator keyGenerator) {
    this.keyGenerator = keyGenerator;
  }

  public Card generate(String cardNumber, String cvc, YearMonth expiryDate, String userId) {
    return new Card(cardNumber, cvc, expiryDate, userId, this.keyGenerator.generateKey());
  }

  @SneakyThrows
  public Card encrypt(Card card, Cipherable cipherable) {
    return new Card(
        cipherable.encrypt(card.getCardNumber(), card.getSecretKey()),
        cipherable.encrypt(card.getCvc(), card.getSecretKey()),
        card.getExpiryDate(),
        card.getUserId(),
        this.keyGenerator.generateKey());
  }

  @SneakyThrows
  public Card decrypt(Card card, Cipherable cipherable) {
    return new Card(
        cipherable.decrypt(card.getCardNumber(), card.getSecretKey()),
        cipherable.decrypt(card.getCvc(), card.getSecretKey()),
        card.getExpiryDate(),
        card.getUserId(),
        this.keyGenerator.generateKey());
  }
}
