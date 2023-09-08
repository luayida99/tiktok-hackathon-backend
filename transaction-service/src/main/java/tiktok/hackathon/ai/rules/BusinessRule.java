package tiktok.hackathon.ai.rules;

import tiktok.hackathon.model.Transaction;

public interface BusinessRule {
  float apply(Transaction transaction, float prevPrediction);
}
