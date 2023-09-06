package tiktok.hackathon.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import tiktok.hackathon.messages.Report;
import tiktok.hackathon.messages.ReportResponse;

@Controller
public class ReportsController {
  // TODO: Implement these methods to do the report and report responses
  @MessageMapping("users/{userId}")
  @SendTo("/banks/{bank}")
  public Report sendReport(@DestinationVariable String userId, @DestinationVariable String bank) {
    return new Report();
  }

  @MessageMapping("/banks/{bank}")
  @SendTo("users/{userId}")
  public ReportResponse returnReport(
      @DestinationVariable String bank, @DestinationVariable String userId) {
    return new ReportResponse();
  }
}
