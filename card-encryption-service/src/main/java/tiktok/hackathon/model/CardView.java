package tiktok.hackathon.model;

import java.time.YearMonth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CardView {
  private String cardNumber;
  private String cvc;
  private YearMonth expiryDate;
}
