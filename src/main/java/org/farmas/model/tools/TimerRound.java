package org.farmas.model.tools;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Stopwatch
 */
public class TimerRound extends Thread {

    private final AtomicBoolean running = new AtomicBoolean(false);
    StopWatch stopWatch;


    public TimerRound(String threadName) {
        this.setName(threadName);
    }

    @Override
    public void run() {
        stopWatch = new StopWatch();
        running.set(true);
        stopWatch.start();
    }

    public void startTimer() {
        stopWatch.start();
    }

    public void stopTimer() {
        stopWatch.stop();
    }

    public void resetTimer() {
        stopWatch.reset();
    }

    public long getTime() {
        return stopWatch.getTime(TimeUnit.SECONDS);
    }


}
