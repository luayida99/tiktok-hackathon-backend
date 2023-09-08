package tiktok.hackathon.exception;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = false)
public class TransactionNotFoundException extends Exception {
  public TransactionNotFoundException(final @NonNull String message) {
    super(message);
  }
}
