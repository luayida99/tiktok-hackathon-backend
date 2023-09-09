package tiktok.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
  private String cardId;
  private String amount;
  private String transactionDateTime;
  private String category;
  private String lat;
  private String lon;
  private String merch_lat;
  private String merch_lon;
  private String dob;
  private String name;
  private String number;
}
