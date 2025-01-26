package spring.ws.database.service;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Getter
@Service
@Scope("singleton")
public class AuctionTimerService {
    private long timeLeft = 0; // Таймер стартует с 120 секунд
    private Boolean start = false;
    public void startTimer() {
        if(!start){
            timeLeft = 120;
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
        System.out.println(timeLeft + " ####################################################");
    }
}
