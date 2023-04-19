package org.example;

public class TimeKeeper implements Runnable {
    private long startTime;
    private long timeLimit;
    private boolean timeUp;

    public TimeKeeper(long timeLimit) {
        this.timeLimit = timeLimit;
        this.timeUp = false;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        while (!timeUp) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime > timeLimit) {
                System.out.println("Time's up! Exploration will be stopped.");
                timeUp = true;
            }
            try {
                Thread.sleep(1000); // Check every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTimeUp() {
        return timeUp;
    }
}