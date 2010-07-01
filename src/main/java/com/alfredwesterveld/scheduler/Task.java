package com.alfredwesterveld.scheduler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *
 * @param <E> 
 * @author alfred
 */
public class Task<E> implements Delayed {

    /**
     * time in millis from Epoch to run task.
     */
    private final long millisFromEpochToSchedule;

    /**
     * Task to retrieve.
     */
    private final E task;

    /**
     * Construct a new to task to run at millis From Epoch.
     * @param task Task to run
     * @param millisFromEpochToSchedule millis from Epoch to run task.
     */
    public Task(final E task, final long millisFromEpochToSchedule) {
        this.task = task;
        this.millisFromEpochToSchedule = millisFromEpochToSchedule;
    }


    @Override
    public long getDelay(TimeUnit tu) {
        final long sourceDuration =
            millisFromEpochToSchedule - System.currentTimeMillis();
        return tu.convert(sourceDuration, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed t) {
        final long thisDelay = getDelay(TimeUnit.MILLISECONDS);
        final long otherDelay = t.getDelay(TimeUnit.MILLISECONDS);
        return  (thisDelay < otherDelay) ? -1 :
                (thisDelay > otherDelay) ? 1 : 0;
    }

    /**
     * Returns task to run.
     * @return task to run.
     */
    public E getTask() {
        return this.task;
    }

    /**
     * Returns time in millis from Epoch to run task.
     * @return time in millis from Epoch.
     */
    public long getMillisFromEpochToSchedule() {
        return millisFromEpochToSchedule;
    }

    @Override
    public String toString() {
        final ConcurrentHashMap<String, Object> toString =
            new ConcurrentHashMap<String, Object>();
        toString.put("time to schedule task", millisFromEpochToSchedule);
        toString.put("task to run", task.toString());
        return toString.toString();
    }
}
