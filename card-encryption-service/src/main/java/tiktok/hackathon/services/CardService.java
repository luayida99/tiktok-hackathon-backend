package tiktok.hackathon.services;

import java.util.List;
import tiktok.hackathon.model.CardView;

public interface CardService {
  void save(
      String cardNumber, String cvc, int expiryYear, int expiryMonth, String userId, String bank);

  List<CardView> retrieveAll(String userId);

  CardView findById(String cardId);
}
