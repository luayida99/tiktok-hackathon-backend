package tiktok.hackathon.services;

import java.time.LocalDate;
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
      LocalDate dateOfBirth,
      String scheme);

  List<CardView> retrieveAll(String userId);
}
