package tiktok.hackathon.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.NonNull;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiktok.hackathon.exception.TransactionNotFoundException;
import tiktok.hackathon.model.Transaction;
import tiktok.hackathon.model.TransactionFactory;
import tiktok.hackathon.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository repository;
  private final TransactionFactory factory;

  @Autowired
  public TransactionServiceImpl(
      final @NonNull TransactionRepository repository, final @NonNull TransactionFactory factory) {
    this.repository = repository;
    this.factory = factory;
  }

  // TODO: Update this method for better logging to FE?
  @Override
  public void add(String cardId, int amount, LocalDateTime transactionDateTime) {
    Transaction completedTransaction = this.factory.generate(cardId, amount, transactionDateTime);
    this.repository.save(completedTransaction);
  }

  @Override
  public List<Transaction> retrieveTransactionsByCard(String cardId) {
    return this.repository.findTransactionsByCard(cardId);
  }

  @Override
  public Transaction findById(String transactionId) throws TransactionNotFoundException {
    return this.repository
        .findById(transactionId)
        .orElseThrow(
            () ->
                new TransactionNotFoundException(
                    "Transaction with %s cannot be found".formatted(transactionId)));
  }
}
