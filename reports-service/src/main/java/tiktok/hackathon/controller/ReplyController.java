package tiktok.hackathon.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tiktok.hackathon.messages.ReportResponse;
import tiktok.hackathon.services.NotificationService;

@RestController
@RequestMapping("/banks")
public class ReplyController {
  NotificationService notificationService;

  @Autowired
  public ReplyController(final @NonNull NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @PostMapping("/{userId}")
  public void sendMessage(@PathVariable String userId, @RequestBody ReportResponse reportResponse) {
    notificationService.sendReportToUser(userId, reportResponse);
  }
}
