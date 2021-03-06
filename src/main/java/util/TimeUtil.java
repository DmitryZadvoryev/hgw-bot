package util;

import java.time.LocalTime;
import java.util.Map;

import static constants.Constants.CD;
import static validation.Validation.require;

public class TimeUtil {

    private LocalTime currentTime;

    private LocalTime lastTry;

    public TimeUtil() {
    }

    public LocalTime getCurrentTime() {
        return currentTime = LocalTime.now();
    }

    public LocalTime getLastTry() {
        return lastTry = LocalTime.now();
    }

    public <T> void initTimer(Map<String, LocalTime> timer, String id) {
        LocalTime value = timer.get(id);
        if (require(value, timer)) {
            LocalTime time = LocalTime.of(1, 0, 0);
            timer.put(id, time);
        }
    }

    public String getTimer(long time) {
        long timer = CD - time;
        if (timer >= 2 && timer <= 4) {
            return "Можешь попробовать поймать снитч через " + timer + " минуты";
        } else if (timer == 1 || timer == 0) {
            return "Можешь попробовать поймать снитч через " + timer + " минуту";
        } else {
            return "Можешь попробовать поймать снитч через " + timer + " минут";
        }
    }
}
