package ru.white.scheduler;

import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {

    private final Timer timer;

    public Scheduler() {
        this.timer = new Timer();
    }

    public void scheduleTask(TimerTask task, long delay, long period) {
        timer.scheduleAtFixedRate(task, delay, period);
    }

    // Другие методы для управления запланированными задачами

    public void cancelTasks() {
        timer.cancel();
    }
}
