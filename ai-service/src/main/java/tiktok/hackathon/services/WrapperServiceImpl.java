package tiktok.hackathon.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tiktok.hackathon.risk.Risk;
import tiktok.hackathon.rules.BusinessRule;

@Service
public class WrapperServiceImpl implements WrapperService {
  private final WebClient client;
  private final ArrayList<BusinessRule> businessRules;

  private static final float epsilon = 0.00001f;

  @Autowired
  public WrapperServiceImpl(final @NonNull WebClient client) {
    this.client = client;
    this.businessRules = new ArrayList<>();
  }

  @Override
  public Risk assess() {
    // TODO: Call AI model here - predictedRisk is dummy value
    float predictedRisk = 0;

    this.initBusinessRules();
    for (BusinessRule rule : this.businessRules) {
      predictedRisk = rule.apply(predictedRisk);
    }

    // TODO: Dummy range values, update
    return withinRange(predictedRisk, 0.00f, 0.20f)
        ? Risk.NONE
        : withinRange(predictedRisk, 0.20f, 0.40f)
            ? Risk.LOW
            : withinRange(predictedRisk, 0.40f, 0.70f) ? Risk.MEDIUM : Risk.HIGH;
  }

  private void initBusinessRules() {
    // TODO: Add business rules to apply here
  }

  // TODO: Check how we wanna do range, epsilon comparison
  private boolean withinRange(float value, float lower, float upper) {
    return value >= lower - epsilon && value < upper + epsilon;
  }
}
