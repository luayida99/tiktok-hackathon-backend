package tiktok.hackathon.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tiktok.hackathon.model.Transaction;

// TODO: Confirm id type
public interface TransactionRepository extends MongoRepository<Transaction, String> {
  @Query("{cardId:?0}")
  List<Transaction> findTransactionsByCard(String cardId);

  @Query("{transactionId:?0}")
  Transaction findTransactionById(String transactionId);
}
