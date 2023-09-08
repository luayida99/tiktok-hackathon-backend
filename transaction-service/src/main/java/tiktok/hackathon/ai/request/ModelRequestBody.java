package tiktok.hackathon.ai.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModelRequestBody {
  private String category;
  private float amt;
  private float lat;
  private float lon;
  private float merch_lat;
  private float merch_lon;
  private int age;
  private int hour;
  private int day;
  private int month;
}
