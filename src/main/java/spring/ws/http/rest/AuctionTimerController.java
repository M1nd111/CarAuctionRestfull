package spring.ws.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.ws.database.service.AuctionTimerService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/timer")
public class AuctionTimerController {
    private final AuctionTimerService timerService;

    @GetMapping("/start")
    public void startTimer() {
        timerService.startTimer();
    }

    @GetMapping("/get")
    public long getTime() {
        return timerService.getTimeLeft();
    }

    @PostMapping("/reset")
    public void resetTimer() {
        timerService.resetTimer();
    }
}
