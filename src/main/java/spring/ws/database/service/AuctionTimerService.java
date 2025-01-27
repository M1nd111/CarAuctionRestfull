package spring.ws.database.service;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Getter
@Service
@Scope("singleton")
public class AuctionTimerService {
    private long timeLeft = 0; // Таймер стартует с 120 секунд
    private Boolean start = false;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public void startTimer() {
        if(!start){
            timeLeft = 30;
            start = true;
        }
    }

    public void resetTimer() {
        timeLeft = 0;
        start = false;
    }

    @Scheduled(fixedRate = 1000)
    public void decreaseTimer() {
        if (start && timeLeft > 0) {
            timeLeft--;
        }
        logger.info("GLOBAL TIMER: %d".formatted(timeLeft));
    }
}
