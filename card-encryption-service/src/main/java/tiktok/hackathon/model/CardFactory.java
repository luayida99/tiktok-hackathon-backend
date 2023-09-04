package tiktok.hackathon.model;

import java.time.YearMonth;
import javax.crypto.KeyGenerator;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tiktok.hackathon.crypto.cipherable.Cipherable;
import tiktok.hackathon.utils.Converter;

@Component
public class CardFactory {
  private KeyGenerator keyGenerator;
  private Converter converter;

  @Autowired
  public CardFactory(final @NonNull KeyGenerator keyGenerator, final @NonNull Converter converter) {
    this.keyGenerator = keyGenerator;
  }

  public Card generate(String cardNumber, String cvc, YearMonth expiryDate, String userId) {
    return new Card(
        cardNumber,
        cvc,
        expiryDate,
        userId,
        converter.keyToString(this.keyGenerator.generateKey()));
  }

  @SneakyThrows
  public Card encrypt(Card card, Cipherable cipherable) {
    return new Card(
        cipherable.encrypt(card.getCardNumber(), card.stringToKey()),
        cipherable.encrypt(card.getCvc(), card.stringToKey()),
        card.getExpiryDate(),
        card.getUserId(),
        card.getSecretKeyString());
  }

  @SneakyThrows
  public Card decrypt(Card card, Cipherable cipherable) {
    System.out.println(card.getSecretKeyString());
    return new Card(
        cipherable.decrypt(card.getCardNumber(), card.stringToKey()),
        cipherable.decrypt(card.getCvc(), card.stringToKey()),
        card.getExpiryDate(),
        card.getUserId(),
        card.getSecretKeyString());
  }
}
