package tiktok.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("transactions")
@Getter
@Setter
@AllArgsConstructor
public class Transaction {
  // TODO: Add fields to be captured in transaction, and metadata for tying back to user/card
}
