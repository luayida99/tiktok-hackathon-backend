package tiktok.hackathon.services;

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
import tiktok.hackathon.request.ModelRequestBody;
import tiktok.hackathon.request.ModelResponseBody;
import tiktok.hackathon.risk.Risk;
import tiktok.hackathon.rules.BusinessRule;

@Service
public class WrapperServiceImpl implements WrapperService {
  private final RestTemplate restTemplate;
  private final ArrayList<BusinessRule> businessRules;

  // TODO: Change this
  private static final String AI_BASE_URL = "http://localhost:5000";

  @Autowired
  public WrapperServiceImpl(final @NonNull RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
    this.businessRules = new ArrayList<>();
  }

  @Override
  public Risk assess() {
    // TODO: Model request body and take in transaction
    // { "category": "travel", "amt": 300, "lat": 40.1362, "long": -95.2138, "merch_lat": 40.591103,
    // "merch_long": -94.445245, "age": 70, "hour": 17, "day": 6, "month": 7 }
    ModelRequestBody modelRequestBody =
        new ModelRequestBody(
            "travel", 300000, 40.1362f, -95.2138f, 40.591103f, -94.445245f, 70, 17, 6, 7);

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
}
