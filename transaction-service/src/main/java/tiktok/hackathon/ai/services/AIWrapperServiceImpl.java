package tiktok.hackathon.ai.services;

import java.time.LocalDate;
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
      predictedRisk = rule.apply(predictedRisk);
    }

    // TODO: Dummy range values, update
    return withinRange(predictedRisk, 0.00f, 0.40f)
        ? Risk.NONE
        : withinRange(predictedRisk, 0.40f, 0.80f) ? Risk.LOW : Risk.HIGH;
  }

  private void initBusinessRules() {
    // TODO: Add business rules to apply here
  }

  private boolean withinRange(float value, float lower, float upper) {
    return value >= lower && value < upper;
  }

  private int computeCurrentAge(LocalDate dateOfBirth) {
    return Period.between(dateOfBirth, LocalDate.now()).getYears();
  }
}
