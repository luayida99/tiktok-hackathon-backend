package tiktok.hackathon;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Commons {
  public static LocalDateTime stringToDate(String dateTimeString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime dateTime = LocalDate.parse(dateTimeString, formatter).atStartOfDay();

    return dateTime;
  }

  public static LocalDateTime stringToDateTime(String dateTimeString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    return LocalDateTime.parse(dateTimeString, formatter);
  }
}
