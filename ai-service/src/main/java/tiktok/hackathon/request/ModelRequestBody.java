package tiktok.hackathon.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModelRequestBody implements Serializable {
  // TODO: long is reserved word - consider lon and merch_lon?
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
