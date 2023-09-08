package tiktok.hackathon.ai.risk;

import lombok.Getter;

// TODO: Check if these levels are sufficient
@Getter
public enum Risk {
  NONE(0),
  LOW(1),
  MEDIUM(2),
  HIGH(3);

  private int riskValue;

  Risk(int riskValue) {
    this.riskValue = riskValue;
  }
}
