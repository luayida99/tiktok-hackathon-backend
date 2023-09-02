package tiktok.hackathon.crypto.cipherable;

public interface Cipherable {
  String encrypt(String textToEncrypt) throws Exception;

  String decrypt(String encryptedText) throws Exception;
}
