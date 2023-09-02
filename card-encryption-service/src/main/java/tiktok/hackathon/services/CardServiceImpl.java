package tiktok.hackathon.services;

import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import tiktok.hackathon.crypto.cipherable.Cipherable;
import tiktok.hackathon.crypto.encdec.AESEncryptionDecryption;
import tiktok.hackathon.model.Card;
import tiktok.hackathon.repository.CardRepository;

public class CardServiceImpl implements CardService {
  private final CardRepository cardRepository;
  private final Cipherable cipherable;

  @Autowired
  public CardServiceImpl(final @NonNull CardRepository cardRepository)
      throws NoSuchPaddingException, NoSuchAlgorithmException {
    this.cardRepository = cardRepository;
    this.cipherable = new AESEncryptionDecryption();
  }

  // TODO: Implement these methods
  @Override
  public String save(Card card) {
    return null;
  }

  @Override
  public String retrieve() {
    return null;
  }
}
