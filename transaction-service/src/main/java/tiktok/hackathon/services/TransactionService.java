package tiktok.hackathon.services;

import java.util.List;

import tiktok.hackathon.ai.risk.Risk;
import tiktok.hackathon.exception.TransactionNotFoundException;
import tiktok.hackathon.model.Transaction;

public interface TransactionService {
  void add(String cardId, String amount, String transactionDateTime, String category, String lat, String lon, String merch_lat, String merch_lon, String age, String name, String number);

  List<Transaction> retrieveTransactionsByCard(String cardId);

  Transaction findById(String transactionId) throws TransactionNotFoundException;

  void updateRisk(String transactionId, Risk risk) throws TransactionNotFoundException;

}
