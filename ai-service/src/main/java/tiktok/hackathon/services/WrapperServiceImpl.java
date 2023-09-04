package tiktok.hackathon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WrapperServiceImpl implements WrapperService {
  private final WebClient client;

  @Autowired
  public WrapperServiceImpl(final @NonNull WebClient client) {
    this.client = client;
  }

  // TODO: Implement methods
}
