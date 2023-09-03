package tiktok.hackathon;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tiktok.hackathon.crypto.encdec.AESEncryptionDecryption;

@Configuration
public class CardEncryptionContainer {
  @Bean
  @NonNull
  AESEncryptionDecryption aesEncryptionDecryption() {
    return new AESEncryptionDecryption();
  }
}
