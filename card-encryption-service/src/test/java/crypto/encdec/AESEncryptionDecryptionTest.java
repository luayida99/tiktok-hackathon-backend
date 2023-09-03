package crypto.encdec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tiktok.hackathon.crypto.encdec.AESEncryptionDecryption;

public class AESEncryptionDecryptionTest {
  AESEncryptionDecryption aes1;
  AESEncryptionDecryption aes2;
  String testString;

  @BeforeEach
  void setUp() throws NoSuchPaddingException, NoSuchAlgorithmException {
    aes1 = new AESEncryptionDecryption();
    aes2 = new AESEncryptionDecryption();
    testString = "0123456789012345";
  }

  @Test
  @DisplayName("Encryption and decryption works as expected")
  void testEncryptDecryptSame() throws Exception {
    // encrypt and decrypt via same AES instance
    String encryptedString = aes1.encrypt(testString);
    String decryptedString = aes1.decrypt(encryptedString);

    assertNotEquals(testString, encryptedString);
    assertEquals(testString, decryptedString);
  }

  @Test
  @DisplayName("Encryption and decryption do not depend on particular AESEncryptionDecryption")
  void testEncryptDecryptDifferent() throws Exception {
    // encrypt and decrypt via different AES instances
    String encryptedString = aes1.encrypt(testString);
    String decryptedString = aes2.decrypt(encryptedString);

    assertNotEquals(testString, encryptedString);
    assertEquals(testString, decryptedString);
  }
}
