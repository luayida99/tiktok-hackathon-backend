package tiktok.hackathon.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.sun.security.auth.UserPrincipal;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserHandshakeHandler extends DefaultHandshakeHandler {

//  @Autowired FirebaseApp firebaseApp;
  FirebaseApp firebaseApp = null;
  @Override
  protected Principal determineUser(
      ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
    System.out.println(request.getURI());
    URI uri = request.getURI();
    UriComponents uriComponents = UriComponentsBuilder.fromUri(uri).build();
    String token = uriComponents.getQueryParams().getFirst("token");
    FirebaseToken decodedToken = null;
    FirebaseOptions options = null;
    try {
      options = new FirebaseOptions.Builder()
              .setCredentials(
                      GoogleCredentials.fromStream(
                              new ClassPathResource("serviceAccountKey.json").getInputStream()))
              .build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    if (FirebaseApp.getApps().isEmpty()) {
      firebaseApp = FirebaseApp.initializeApp(options);
    } else{
      firebaseApp = FirebaseApp.getInstance();
    }
    try {
      decodedToken = FirebaseAuth.getInstance(firebaseApp).verifyIdToken(token);
    } catch (FirebaseAuthException e) {
      throw new RuntimeException(e);
    }
    String uid = decodedToken.getUid();
    System.out.println(uid);
    return new UserPrincipal(uid);
  }
}
