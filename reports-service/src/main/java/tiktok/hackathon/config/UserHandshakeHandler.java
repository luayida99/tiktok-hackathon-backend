package tiktok.hackathon.config;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.sun.security.auth.UserPrincipal;
import java.net.URI;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserHandshakeHandler extends DefaultHandshakeHandler {

  @Autowired FirebaseApp firebaseApp;

  @Override
  protected Principal determineUser(
      ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
    System.out.println(request.getURI());
    URI uri = request.getURI();
    UriComponents uriComponents = UriComponentsBuilder.fromUri(uri).build();
    String token = uriComponents.getQueryParams().getFirst("token");
    FirebaseToken decodedToken = null;
    //    try {
    //      options =
    //          FirebaseOptions.builder()
    //              .setCredentials(
    //                  GoogleCredentials.fromStream(
    //                      new ClassPathResource("serviceAccountKey.json").getInputStream()))
    //              .build();
    //    } catch (IOException e) {
    //      throw new RuntimeException(e);
    //    }
    //
    //    firebaseApp = FirebaseApp.initializeApp(options);
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
