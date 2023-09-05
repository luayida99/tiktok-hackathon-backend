package tiktok.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
class TransactionsService {
  public static void main(String[] args) {
    SpringApplication.run(TransactionsService.class);
  }
}
