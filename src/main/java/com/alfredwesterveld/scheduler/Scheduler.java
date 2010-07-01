/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alfredwesterveld.scheduler;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @param <E> 
 * @author alfred
 */
public class Scheduler<E> {
    private final DelayQueue<Task<E>> delayedJobQueue;
    private final AtomicInteger totalTask;
    private final AtomicInteger taskRetrieved;

    public Scheduler() {
        this.delayedJobQueue = new DelayQueue<Task<E>>();
        this.totalTask = new AtomicInteger(0);
        this.taskRetrieved = new AtomicInteger(0);
    }

    public void schedule(final Task<E> taskToSchedule) {
        delayedJobQueue.put(taskToSchedule);
        totalTask.incrementAndGet();
    }

    public Task<E> peek() {
        return delayedJobQueue.peek();
    }

    public Task<E> get() throws InterruptedException {
        try {
            return delayedJobQueue.take();
        } finally {
            taskRetrieved.incrementAndGet();
        }
    }

    public int getTaskRetrieved() {
        return taskRetrieved.get();
    }

    public int getTotalTaskScheduled() {
        return totalTask.get();
    }
}
