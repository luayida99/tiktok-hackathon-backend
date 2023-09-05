package tiktok.hackathon.services;

import java.time.YearMonth;
import java.util.List;
import tiktok.hackathon.model.CardView;

public interface CardService {
  String save(String cardNumber, String cvc, YearMonth expiryDate, String userId);

  List<CardView> retrieveAll(String userId);
}
