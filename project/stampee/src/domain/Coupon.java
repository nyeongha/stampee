package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Coupon {
  private long couponId;
  private LocalDateTime createTime;
  private LocalDateTime endTime;
  private List<Member> members;
  private List<Cafe> cafes;
}
