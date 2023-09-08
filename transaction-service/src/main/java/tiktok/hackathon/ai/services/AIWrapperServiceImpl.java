package tiktok.hackathon.ai.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tiktok.hackathon.ai.request.ModelRequestBody;
import tiktok.hackathon.ai.request.ModelResponseBody;
import tiktok.hackathon.ai.risk.Risk;
import tiktok.hackathon.ai.rules.BusinessRule;
import tiktok.hackathon.model.Transaction;

@Service
public class AIWrapperServiceImpl implements AIWrapperService {
  private final RestTemplate restTemplate;
  private final ArrayList<BusinessRule> businessRules;

  // TODO: Change this
  private static final String AI_BASE_URL = "http://localhost:5000";

  @Autowired
  public AIWrapperServiceImpl(final @NonNull RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
    this.businessRules = new ArrayList<>();
  }

  @Override
  public Risk assess(Transaction transaction) {
    // TODO: Model request body and take in transaction
    // { "category": "travel", "amt": 300, "lat": 40.1362, "long": -95.2138, "merch_lat": 40.591103,
    // "merch_long": -94.445245, "age": 70, "hour": 17, "day": 6, "month": 7 }
    ModelRequestBody modelRequestBody =
        new ModelRequestBody(
            transaction.getCategory(),
            transaction.getAmount(),
            transaction.getLat(),
            transaction.getLon(),
            transaction.getMerch_lat(),
            transaction.getMerch_lon(),
            this.computeCurrentAge(transaction.getDateOfBirth()),
            transaction.getTransactionDateTime().getHour(),
            transaction.getTransactionDateTime().getDayOfMonth(),
            transaction.getTransactionDateTime().getMonthValue());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<ModelRequestBody> request = new HttpEntity<>(modelRequestBody, headers);
    ResponseEntity<ModelResponseBody> response =
        this.restTemplate.postForEntity(AI_BASE_URL + "/predict", request, ModelResponseBody.class);
    float predictedRisk = response.getBody().getFraudPr();

    this.initBusinessRules();
    for (BusinessRule rule : this.businessRules) {
      predictedRisk = rule.apply(transaction, predictedRisk);
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

  private boolean withinRange(float value, float lower, float upper) {
    return value >= lower && value < upper;
  }

  private int computeCurrentAge(LocalDateTime dateOfBirth) {
    return Period.between(dateOfBirth.toLocalDate(), LocalDate.now()).getYears();
  }
}
