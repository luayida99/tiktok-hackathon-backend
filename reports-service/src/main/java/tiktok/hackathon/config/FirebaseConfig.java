package tiktok.hackathon.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FirebaseConfig {

  @Primary
  @Bean
  public FirebaseApp getfirebaseApp() throws IOException {
    FirebaseOptions options =
        FirebaseOptions.builder()
            .setCredentials(
                GoogleCredentials.fromStream(
                    new ClassPathResource("serviceAccountKey.json").getInputStream()))
            .build();

    if (FirebaseApp.getApps().isEmpty()) {
      FirebaseApp.initializeApp(options);
    }
    return FirebaseApp.getInstance();
  }

  @Bean
  public FirebaseAuth getAuth() throws IOException {
    return FirebaseAuth.getInstance(getfirebaseApp());
  }
}
