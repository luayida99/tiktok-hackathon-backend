package tiktok.hackathon.services;

import java.util.List;
import tiktok.hackathon.model.Card;

public interface CardService {
  String save(Card card);

  List<Card> retrieveAll(String userId);
}
