package tiktok.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
