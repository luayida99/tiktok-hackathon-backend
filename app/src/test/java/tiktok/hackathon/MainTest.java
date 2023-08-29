package tiktok.hackathon;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MainTest {
  @Test
  @DisplayName("Empty test to ensure dependencies setup correctly")
  void emptyTest() {
    assertTrue(1 == 1);
  }
}
