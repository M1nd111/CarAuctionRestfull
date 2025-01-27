package spring.ws.database.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class AuctionTimerService {
    private final Map<String, Long> timers = new ConcurrentHashMap<>(); // Таймеры для каждого авто
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final long DEFAULT_TIME = 60; // Стандартное время для нового таймера

    // Получение времени для авто, создание таймера при отсутствии
    public long getTimeLeft(String autoNumber) {
        timers.putIfAbsent(autoNumber, DEFAULT_TIME); // Добавить таймер, если его нет
        return timers.get(autoNumber); // Вернуть текущее значение
    }

    // Запуск таймера (обновление, если уже существует)
    public void startTimer(String autoNumber) {
        timers.put(autoNumber, DEFAULT_TIME); // Устанавливаем таймер на стандартное время
    }

    // Сброс таймера для конкретного авто
    public void resetTimer(String autoNumber) {
        timers.remove(autoNumber); // Удаляем таймер для данного авто
    }

    // Уменьшение всех таймеров каждую секунду
    @Scheduled(fixedRate = 1000)
    public void decreaseTimers() {
        timers.forEach((autoNumber, timeLeft) -> {
            if (timeLeft > 0) {
                timers.put(autoNumber, timeLeft - 1); // Уменьшаем таймер
            } else {
                timers.remove(autoNumber); // Удаляем таймер, если время вышло
            }
            logger.info("TIMER [%s]: %d".formatted(autoNumber, timeLeft));
        });
    }
}
