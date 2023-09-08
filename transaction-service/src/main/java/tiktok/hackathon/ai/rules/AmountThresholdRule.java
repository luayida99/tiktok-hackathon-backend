package tiktok.hackathon.ai.rules;

import tiktok.hackathon.model.Transaction;

public class AmountThresholdRule implements BusinessRule {
    private int threshold = 1000;

    @Override
    public float apply(Transaction transaction, float prevPrediction) {
        if (transaction.getAmount() > threshold) {
            return Math.max(prevPrediction + 0.1f, 1.0f);
        }
        return prevPrediction;
    }
}
