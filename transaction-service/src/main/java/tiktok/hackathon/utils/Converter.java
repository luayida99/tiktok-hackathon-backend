package tiktok.hackathon.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class Converter {
    public LocalDateTime stringToDate(String dateTimeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

        return dateTime;
    }

    public LocalDateTime stringToDateTime(String dateTimeString){
        // TODO: Implement converter for tx datetime
        return null;
    }
}
