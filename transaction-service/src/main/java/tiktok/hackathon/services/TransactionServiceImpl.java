package tiktok.hackathon.services;

import java.util.List;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiktok.hackathon.Commons;
import tiktok.hackathon.ai.risk.Risk;
import tiktok.hackathon.ai.services.AIWrapperService;
import tiktok.hackathon.exception.TransactionNotFoundException;
import tiktok.hackathon.model.Transaction;
import tiktok.hackathon.model.TransactionFactory;
import tiktok.hackathon.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository repository;
  private final TransactionFactory factory;
  private final AIWrapperService aiWrapperService;

  @Autowired
  public TransactionServiceImpl(
      final @NonNull TransactionRepository repository,
      final @NonNull TransactionFactory factory,
      final @NonNull AIWrapperService aiWrapperService) {
    this.repository = repository;
    this.factory = factory;
    this.aiWrapperService = aiWrapperService;
  }

  // TODO: Update how FE passes dateOfBirth, replace age
  @Override
  public void add(
      String cardId,
      String amount,
      String transactionDateTime,
      String category,
      String lat,
      String lon,
      String merch_lat,
      String merch_lon,
      String dob,
      String name,
      String number) {
    Transaction completedTransaction =
        this.factory.generate(
            cardId,
            Float.parseFloat(amount),
            Commons.stringToDateTime(transactionDateTime),
            category,
            Float.parseFloat(lat),
            Float.parseFloat(lon),
            Float.parseFloat(merch_lat),
            Float.parseFloat(merch_lon),
            Commons.stringToDate(dob),
            name,
            number);
    System.out.println("2");
    Risk risk = aiWrapperService.assess(completedTransaction);
    System.out.println("3");
    completedTransaction.setRisk(risk);
    System.out.println("4");
    this.repository.save(completedTransaction);
    System.out.println("5");
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

  @Override
  public void updateRisk(String transactionId, Risk risk) throws TransactionNotFoundException {
    Transaction tx =
        this.repository
            .findById(transactionId)
            .orElseThrow(
                () ->
                    new TransactionNotFoundException(
                        "Transaction with %s cannot be found".formatted(transactionId)));

    tx.setRisk(risk);
    this.repository.save(tx);
  }
}
