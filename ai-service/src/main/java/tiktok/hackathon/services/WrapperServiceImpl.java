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

  @Autowired
  public WrapperServiceImpl(final @NonNull WebClient client) {
    this.client = client;
    this.businessRules = new ArrayList<>();
  }

  @Override
  public Risk assess() {
    // TODO: Call AI model here - predictedRisk is dummy value
    double predictedRisk = 0;

    this.initBusinessRules();
    for (BusinessRule rule : this.businessRules) {
      predictedRisk = rule.apply(predictedRisk);
    }

    // TODO: Dummy range values, update
    return withinRange(predictedRisk, 0.00, 0.20)
        ? Risk.NONE
        : withinRange(predictedRisk, 0.20, 0.40)
            ? Risk.LOW
            : withinRange(predictedRisk, 0.40, 0.70) ? Risk.MEDIUM : Risk.HIGH;
  }

  private void initBusinessRules() {
    // TODO: Add business rules to apply here
  }

  // TODO: Check how we wanna do range
  private boolean withinRange(double value, double lower, double upper) {
    return value >= lower && value < upper;
  }
}
