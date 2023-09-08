package tiktok.hackathon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Commons {
  public static LocalDateTime stringToDate(String dateTimeString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

    return dateTime;
  }

  public static LocalDateTime stringToDateTime(String dateTimeString) {
    // TODO: Implement converter for tx datetime
    return null;
  }
}
