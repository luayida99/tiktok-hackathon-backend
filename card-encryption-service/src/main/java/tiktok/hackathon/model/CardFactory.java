package tiktok.hackathon.model;

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
    this.converter = converter;
  }

  public Card generate(
      String cardNumber, String cvc, int expiryYear, int expiryMonth, String userId, String bank) {
    return new Card(
        cardNumber,
        cvc,
        expiryYear,
        expiryMonth,
        userId,
        bank,
        converter.keyToString(this.keyGenerator.generateKey()));
  }

  @SneakyThrows
  public Card encrypt(Card card, Cipherable cipherable) {
    return new Card(
        cipherable.encrypt(card.getCardNumber(), card.stringToKey()),
        cipherable.encrypt(card.getCvc(), card.stringToKey()),
        card.getExpiryYear(),
        card.getExpiryMonth(),
        card.getUserId(),
        card.getBank(),
        card.getSecretKeyString());
  }

  @SneakyThrows
  public Card decrypt(Card card, Cipherable cipherable) {
    return new Card(
        cipherable.decrypt(card.getCardNumber(), card.stringToKey()),
        cipherable.decrypt(card.getCvc(), card.stringToKey()),
        card.getExpiryYear(),
        card.getExpiryMonth(),
        card.getUserId(),
        card.getBank(),
        card.getSecretKeyString());
  }
}
