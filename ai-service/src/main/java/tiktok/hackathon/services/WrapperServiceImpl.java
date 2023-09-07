package tiktok.hackathon.services;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tiktok.hackathon.request.ModelRequestBody;
import tiktok.hackathon.request.ModelReturnBody;
import tiktok.hackathon.risk.Risk;
import tiktok.hackathon.rules.BusinessRule;

@Getter
@Setter
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
  public Risk assess() throws ExecutionException, InterruptedException {
    // TODO: Model request body and take in transaction
    ModelRequestBody modelRequestBody =
        new ModelRequestBody("test", 100f, 100f, 100f, 10f, 10f, 20, 4, 3, 2);

    ModelReturnBody modelReturnBody =
        this.client
            .post()
            .uri("/predict")
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .bodyValue(modelRequestBody)
            .retrieve()
            .bodyToMono(ModelReturnBody.class)
            .block();

    float predictedRisk = modelReturnBody.getFraudPr();
    System.out.println(predictedRisk);

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
