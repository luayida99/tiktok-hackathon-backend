package tiktok.hackathon.utils;

import java.util.Base64;
import javax.crypto.SecretKey;

public class Converter {
  public String keyToString(SecretKey secretKey) {
    byte[] keyBytes = secretKey.getEncoded();
    return Base64.getEncoder().encodeToString(keyBytes);
  }
}
