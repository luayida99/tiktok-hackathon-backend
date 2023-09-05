package tiktok.hackathon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tiktok.hackathon.model.Transaction;

// TODO: Confirm id type
public interface TransactionRepository extends MongoRepository<Transaction, String> {}
