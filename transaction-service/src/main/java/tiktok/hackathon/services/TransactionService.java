package tiktok.hackathon.services;

import java.util.Date;
import java.util.List;
import tiktok.hackathon.model.Transaction;

public interface TransactionService {
  void add(String cardId, int amount, Date transactionDateTime);

  List<Transaction> retrieveTransactionsByCard(String cardId);

  Transaction findById(String transactionId) throws RuntimeException;
}
