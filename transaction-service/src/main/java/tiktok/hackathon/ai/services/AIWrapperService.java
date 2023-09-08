package tiktok.hackathon.ai.services;

import tiktok.hackathon.ai.risk.Risk;
import tiktok.hackathon.model.Transaction;

public interface AIWrapperService {
  Risk assess(Transaction transaction);
}
