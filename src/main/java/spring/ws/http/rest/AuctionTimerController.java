package spring.ws.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.ws.database.service.AuctionTimerService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/timer")
public class AuctionTimerController {
    private final AuctionTimerService timerService;

    @GetMapping("/start")
    public void startTimer(@RequestParam String autoNumber) {
        timerService.startTimer(autoNumber);
    }

    @GetMapping("/get")
    public long getTime(@RequestParam String autoNumber) {
        return timerService.getTimeLeft(autoNumber);
    }

    @GetMapping("/reset")
    public void resetTimer(@RequestParam String autoNumber) {
        timerService.resetTimer(autoNumber);
    }
}
