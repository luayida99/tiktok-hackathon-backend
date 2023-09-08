package tiktok.hackathon.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tiktok.hackathon.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
  @Query("{cardId:?0}")
  List<Transaction> findTransactionsByCard(String cardId);
}
