package tiktok.hackathon;

import javax.crypto.KeyGenerator;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tiktok.hackathon.crypto.cipherable.Cipherable;
import tiktok.hackathon.crypto.encdec.AESEncryptionDecryption;
import tiktok.hackathon.utils.Converter;

@Configuration
public class CardEncryptionContainer {

  @Bean
  @NonNull
  Converter converter() {
    return new Converter();
  }

  @Bean
  @NonNull
  Cipherable cipherable() {
    return new AESEncryptionDecryption();
  }

  @Bean
  @NonNull
  @SneakyThrows
  KeyGenerator keyGenerator() {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128);

    return keyGenerator;
  }
}
