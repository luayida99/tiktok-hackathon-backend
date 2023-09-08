package tiktok.hackathon.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FirebaseConfig {

  //    @Bean
  //    public FirebaseApp getFirebaseApp (){
  //        FirebaseOptions options = null;
  //        try {
  //            options =
  //                    FirebaseOptions.builder()
  //                            .setCredentials(
  //                                    GoogleCredentials.fromStream(
  //                                            new
  // ClassPathResource("serviceAccountKey.json").getInputStream()))
  //                            .build();
  //        } catch (IOException e) {
  //            throw new RuntimeException(e);
  //        }
  //
  //        return FirebaseApp.initializeApp(options);
  //    }

  @Bean
  public FirebaseApp createFireBaseApp() throws IOException {

    FirebaseOptions options =
        new FirebaseOptions.Builder()
            .setCredentials(
                GoogleCredentials.fromStream(
                    new ClassPathResource("serviceAccountKey.json").getInputStream()))
            .build();

    // Add loggers
    System.out.println("Firebase config initialized");

    FirebaseApp app = FirebaseApp.initializeApp(options);
    System.out.println(app);
    return app;
  }
}
