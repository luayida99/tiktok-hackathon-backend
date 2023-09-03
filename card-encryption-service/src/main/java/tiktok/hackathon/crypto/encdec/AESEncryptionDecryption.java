package tiktok.hackathon.crypto.encdec;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import tiktok.hackathon.crypto.cipherable.Cipherable;

public class AESEncryptionDecryption implements Cipherable {
  private static Cipher cipher;
  private static SecretKey secretKey;

  @Autowired
  @SneakyThrows
  public AESEncryptionDecryption() {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128);

    this.cipher = Cipher.getInstance("AES");
    this.secretKey = keyGenerator.generateKey();
  }

  @Override
  public String encrypt(String textToEncrypt) throws Exception {
    byte[] plainTextByte = textToEncrypt.getBytes();
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptedBytes = cipher.doFinal(plainTextByte);
    Base64.Encoder encoder = Base64.getEncoder();
    String encryptedText = encoder.encodeToString(encryptedBytes);

    return encryptedText;
  }

  @Override
  public String decrypt(String encryptedText) throws Exception {
    Base64.Decoder decoder = Base64.getDecoder();
    byte[] encryptedTextByte = decoder.decode(encryptedText);
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
    String decryptedText = new String(decryptedByte);

    return decryptedText;
  }
}
