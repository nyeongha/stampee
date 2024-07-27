package service;

import static java.util.concurrent.TimeUnit.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Scheduler {
	private static final String SEOUL = "Asia/Seoul";
	private static final int ONE_DAY = 1;
	private static final int ON_DAY_SECOND = 24 * 60 * 60;
	private static final int SINGLE_POOL_SIZE = 1;

	private final ScheduledExecutorService scheduler;

	public Scheduler() {
		this.scheduler = Executors.newScheduledThreadPool(SINGLE_POOL_SIZE);
	}

	public void execute(Runnable service){	//주어진 시간에 작업을 시작하고 이후 매일 반복 실행하는 메서드
		ZonedDateTime now = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(SEOUL));	//현재 시간을 서울 기준으로 가져옴
		ZonedDateTime nextExecutionTime = getNextExecutionTime(now);	//다음 실행 시간 계산
		scheduler.scheduleAtFixedRate(service, getInitialExecutionTime(now, nextExecutionTime), ON_DAY_SECOND, SECONDS);	//주어진 초기 지연 시간 후 매일 반복 실행
	}

	private ZonedDateTime getNextExecutionTime(ZonedDateTime now){
		ZonedDateTime next = now.withHour(0).withMinute(0).withSecond(0);	//자정

		if(isOverDay(now, next)){	//현재 시간이 지정된 시간 이후인지 확인
			next = next.plusDays(ONE_DAY);	//하루 추가
		}

		return next;
	}

	private boolean isOverDay(ZonedDateTime now, ZonedDateTime next) {
		return now.compareTo(next) > 0;
	}

	private long getInitialExecutionTime(ZonedDateTime now, ZonedDateTime next) {
		Duration duration = Duration.between(now, next);
		return duration.getSeconds();
	}
}
