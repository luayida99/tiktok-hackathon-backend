package tiktok.hackathon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tiktok.hackathon.model.Card;

// TODO: What methods to call here?
public interface CardRepository extends MongoRepository<Card, String> {}
