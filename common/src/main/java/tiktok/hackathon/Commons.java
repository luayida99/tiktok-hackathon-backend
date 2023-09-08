package tiktok.hackathon;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Commons {
  public static LocalDate stringToDate(String dateTimeString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    return LocalDate.parse(dateTimeString, formatter);
  }

  public static LocalDateTime stringToDateTime(String dateTimeString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    return LocalDateTime.parse(dateTimeString, formatter);
  }
}
