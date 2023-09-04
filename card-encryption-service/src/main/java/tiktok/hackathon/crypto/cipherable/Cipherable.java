package tiktok.hackathon.crypto.cipherable;

import javax.crypto.SecretKey;

public interface Cipherable {
  String encrypt(String textToEncrypt, SecretKey secretKey) throws Exception;

  String decrypt(String encryptedText, SecretKey secretKey) throws Exception;
}
