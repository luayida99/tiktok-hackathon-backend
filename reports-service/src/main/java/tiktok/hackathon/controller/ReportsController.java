package tiktok.hackathon.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import tiktok.hackathon.messages.Report;
import tiktok.hackathon.messages.ReportResponse;

@Controller
public class ReportsController {
  // TODO: Implement these methods to do the report and report responses
  //  messageMapping receives subscriptions
  //  sendTo broadcasts to subscribers

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

  @MessageMapping("/sendMessage")
  @SendTo("/topic/private")
  public String sendMessage(@Payload String msg, @RequestParam String user) {
    System.out.println("testing" + user);
    return msg + user;
  }
}
