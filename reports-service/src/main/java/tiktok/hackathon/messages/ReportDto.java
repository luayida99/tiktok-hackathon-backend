package tiktok.hackathon.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportDto {
  private String token;
  private String bank;
  private String description;
  //    private String txId;
}
