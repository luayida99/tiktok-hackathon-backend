package tiktok.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("transactions")
@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    @Id
    private String transactionId;
    private String cardId;
    private int amount;
    private LocalDateTime transactionDateTime;
    private String category;
    private float lat;
    private float lon;
    private float merch_lat;
    private float merch_lon;
    private int age;
    private String name;
    private String number;
}