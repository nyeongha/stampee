package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Stamp {
  private long stampId;
  private int count;
  private LocalDateTime createTime;
  private List<Member> members;
  private List<Cafe> cafes;
}
