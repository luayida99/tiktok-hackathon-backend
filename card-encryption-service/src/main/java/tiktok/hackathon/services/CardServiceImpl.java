package tiktok.hackathon.services;

import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiktok.hackathon.crypto.cipherable.Cipherable;
import tiktok.hackathon.crypto.encdec.AESEncryptionDecryption;
import tiktok.hackathon.model.Card;
import tiktok.hackathon.repository.CardRepository;

@Service
public class CardServiceImpl implements CardService {
  private final CardRepository cardRepository;
  private final Cipherable cipherable;

  @Autowired
  public CardServiceImpl(
      final @NonNull CardRepository cardRepository,
      final @NonNull AESEncryptionDecryption cipherable) {
    this.cardRepository = cardRepository;
    this.cipherable = cipherable;
  }

  // TODO: Not tested
  @Override
  public String save(Card card) {
    // TODO: Is returning card number necessary?
    return this.cardRepository.save(card.encrypt(this.cipherable)).getCardNumber();
  }

  @Override
  public List<Card> retrieveAll(String userId) {
    List<Card> encryptedCards = this.cardRepository.findCardsByUserId(userId);

    return encryptedCards.stream()
        .map(card -> card.decrypt(this.cipherable))
        .collect(Collectors.toList());
  }
}
