package tiktok.hackathon.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import tiktok.hackathon.messages.ReportDto;
import tiktok.hackathon.messages.ReportResponse;

@Controller
public class ReportsController {
  FirebaseApp firebaseApp = null;
  FirebaseToken decodedToken = null;

  @MessageMapping("report")
  @SendTo("/topic/banks")
  public ReportResponse sendReport(@Payload ReportDto report) {
    FirebaseOptions options = null;
    String uid = null;
    try {
      options =
          new FirebaseOptions.Builder()
              .setCredentials(
                  GoogleCredentials.fromStream(
                      new ClassPathResource("serviceAccountKey.json").getInputStream()))
              .build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    if (FirebaseApp.getApps().isEmpty()) {
      firebaseApp = FirebaseApp.initializeApp(options);
    } else {
      firebaseApp = FirebaseApp.getInstance();
    }

    try {
      decodedToken = FirebaseAuth.getInstance(firebaseApp).verifyIdToken(report.getToken());
      uid = decodedToken.getUid();
    } catch (FirebaseAuthException e) {
      throw new RuntimeException(e);
    }
    return new ReportResponse(uid, report.getDescription(), report.getBank(), "test");
  }

  @MessageMapping("/sendMessage")
  @SendTo("/topic/private")
  public String sendMessage(@Payload String msg, @RequestParam String user) {
    return msg + user;
  }
}
