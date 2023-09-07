package tiktok.hackathon.services;

import java.util.concurrent.ExecutionException;
import tiktok.hackathon.risk.Risk;

public interface WrapperService {
  Risk assess() throws ExecutionException, InterruptedException;
}
