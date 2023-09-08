package tiktok.hackathon.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiktok.hackathon.crypto.cipherable.Cipherable;
import tiktok.hackathon.model.Card;
import tiktok.hackathon.model.CardFactory;
import tiktok.hackathon.model.CardView;
import tiktok.hackathon.repository.CardRepository;

@Service
public class CardServiceImpl implements CardService {
  private final CardRepository cardRepository;
  private final Cipherable cipherable;
  private final CardFactory cardFactory;

  @Autowired
  public CardServiceImpl(
      final @NonNull CardRepository cardRepository,
      final @NonNull CardFactory cardFactory,
      final @NonNull Cipherable cipherable) {
    this.cardRepository = cardRepository;
    this.cardFactory = cardFactory;
    this.cipherable = cipherable;
  }

  @Override
  public void save(
      String cardNumber,
      String cvc,
      int expiryYear,
      int expiryMonth,
      String userId,
      String bank,
      LocalDateTime dateOfBirth,
      String scheme) {
    Card completedCard =
        this.cardFactory.generate(
            cardNumber, cvc, expiryYear, expiryMonth, userId, bank, dateOfBirth, scheme);
    Card encryptedCard = this.cardFactory.encrypt(completedCard, this.cipherable);

    this.cardRepository.save(encryptedCard).getCardNumber();
  }

  @Override
  public List<CardView> retrieveAll(String userId) {
    List<Card> encryptedCards = this.cardRepository.findCardsByUserId(userId);

    return encryptedCards.stream()
        .map(card -> this.cardFactory.decrypt(card, this.cipherable))
        .map(Card::getView)
        .collect(Collectors.toList());
  }
}
