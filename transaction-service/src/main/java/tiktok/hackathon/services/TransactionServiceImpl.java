package tiktok.hackathon.services;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiktok.hackathon.model.Transaction;
import tiktok.hackathon.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository repository;

  @Autowired
  public TransactionServiceImpl(final @NonNull TransactionRepository repository) {
    this.repository = repository;
  }

  // TODO: Update this method for better logging to FE?
  @Override
  public void add(Transaction transaction) {
    this.repository.save(transaction);
  }
}
