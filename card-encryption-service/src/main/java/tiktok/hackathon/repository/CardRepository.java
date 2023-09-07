package tiktok.hackathon.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tiktok.hackathon.model.Card;

public interface CardRepository extends MongoRepository<Card, String> {
  @Query("{userId:'?0'}")
  List<Card> findCardsByUserId(String userId);
}
