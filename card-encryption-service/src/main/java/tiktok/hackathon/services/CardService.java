package tiktok.hackathon.services;

import java.time.LocalDateTime;
import java.util.List;
import tiktok.hackathon.model.CardView;

public interface CardService {
  void save(
      String cardNumber,
      String cvc,
      int expiryYear,
      int expiryMonth,
      String userId,
      String bank,
      LocalDateTime dateOfBirth);

  List<CardView> retrieveAll(String userId);
}
