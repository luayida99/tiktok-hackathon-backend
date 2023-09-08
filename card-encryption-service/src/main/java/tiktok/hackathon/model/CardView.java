package tiktok.hackathon.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CardView {
  private String cardNumber;
  private String cvc;
  private int expiryYear;
  private int expiryMonth;
  private String bank;
  private String cardId;
  private String scheme;
  private LocalDate dateOfBirth;
}
