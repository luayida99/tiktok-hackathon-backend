package tiktok.hackathon.services;

import java.time.YearMonth;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiktok.hackathon.crypto.cipherable.Cipherable;
import tiktok.hackathon.model.Card;
import tiktok.hackathon.model.CardFactory;
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
  public String save(String cardNumber, String cvc, YearMonth expiryDate, String userId) {
    // TODO: Is returning card number necessary?
    Card completedCard = this.cardFactory.generate(cardNumber, cvc, expiryDate, userId);
    Card encryptedCard = this.cardFactory.encrypt(completedCard, this.cipherable);

    return this.cardRepository.save(encryptedCard).getCardNumber();
  }

  @Override
  public List<Card> retrieveAll(String userId) {
    List<Card> encryptedCards = this.cardRepository.findCardsByUserId(userId);
    Logger logger = Logger.getLogger("");
    logger.info(encryptedCards.toString());
    return encryptedCards.stream()
        .map(card -> this.cardFactory.decrypt(card, this.cipherable))
        .collect(Collectors.toList());
  }
}
