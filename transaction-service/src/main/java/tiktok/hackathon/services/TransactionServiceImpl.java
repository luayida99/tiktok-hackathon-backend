package tiktok.hackathon.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.NonNull;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiktok.hackathon.ai.services.AIWrapperService;
import tiktok.hackathon.ai.services.AIWrapperService;
import tiktok.hackathon.exception.TransactionNotFoundException;
import tiktok.hackathon.model.Transaction;
import tiktok.hackathon.model.TransactionFactory;
import tiktok.hackathon.repository.TransactionRepository;
import tiktok.hackathon.utils.Converter;

@Service
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository repository;
  private final TransactionFactory factory;
  private final Converter converter;
  private final AIWrapperService aiWrapperService;

  @Autowired
  public TransactionServiceImpl(
      final @NonNull TransactionRepository repository,
      final @NonNull TransactionFactory factory,
      final @NonNull Converter converter,
      final @NonNull AIWrapperService aiWrapperService) {
    this.repository = repository;
    this.factory = factory;
    this.converter = converter;
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
      String age,
      String name,
      String number) {
    Transaction completedTransaction =
        this.factory.generate(
            cardId,
            Integer.parseInt(amount),
            converter.stringToDate(transactionDateTime),
            category,
            Float.parseFloat(lat),
            Float.parseFloat(lon),
            Float.parseFloat(merch_lat),
            Float.parseFloat(merch_lon),
            Integer.parseInt(age),
            name,
            number);
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
