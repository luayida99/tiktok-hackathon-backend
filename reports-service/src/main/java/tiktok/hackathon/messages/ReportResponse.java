package tiktok.hackathon.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportResponse {
  private String userId;
  private String bank;
  private String description;
  private String txId;
}
