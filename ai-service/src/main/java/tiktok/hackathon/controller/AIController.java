package tiktok.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tiktok.hackathon.risk.Risk;
import tiktok.hackathon.services.WrapperService;

@RestController
@RequestMapping("/ai")
public class AIController {
  private final WrapperService wrapperService;

  @Autowired
  public AIController(final @NonNull WrapperService wrapperService) {
    this.wrapperService = wrapperService;
  }
  // TODO: Implement controller methods for exposing AI
  @GetMapping
  public ResponseEntity<Risk> assess() {
    try {
      return ResponseEntity.ok(this.wrapperService.assess());
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return ResponseEntity.internalServerError().build();
    }
  }
}
