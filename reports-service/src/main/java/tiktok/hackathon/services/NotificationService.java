package tiktok.hackathon.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import tiktok.hackathon.messages.ReportDto;
import tiktok.hackathon.messages.ReportResponse;

public class NotificationService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    public void sendReportToUser(String userId, ReportResponse reportResponse) {
        simpMessagingTemplate.convertAndSendToUser(userId, "/topic/banks", reportResponse);
    }
}
