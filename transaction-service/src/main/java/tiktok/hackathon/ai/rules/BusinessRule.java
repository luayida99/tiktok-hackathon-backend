package tiktok.hackathon.ai.rules;

public interface BusinessRule {
  float apply(float prevPrediction);
}
