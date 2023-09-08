package tiktok.hackathon.ai.rules;

import tiktok.hackathon.model.Transaction;

public class OddHoursRule implements BusinessRule {
    private int oddHourStart = 1;
    private int oddHourEnd = 5;

    @Override
    public float apply(Transaction transaction, float prevPrediction) {
        if (oddHourStart <= transaction.getTransactionDateTime().getHour() <= oddHourEnd) {
            return Math.max(prevPrediction + 0.1f, 1.0f);
        }
        return prevPrediction;
    }
}
