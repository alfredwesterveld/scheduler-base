package com.alfredwesterveld.scheduler;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alfred
 */
public class SchedulerTest {
    private final Scheduler<String> scheduler = new Scheduler<String>();

    @Test
    public void scheduleSomeTaskAddedInReverseOrder() throws Exception {
        final Task<String> firstTask = new Task<String>("run first", 1);
        final Task<String> secondTask = new Task<String>("run second", 2);
        // First schedule second task, but get last.
        scheduler.schedule(secondTask);
        // Next schedule first task, which get first.
        scheduler.schedule(firstTask);
        Task<String> first = scheduler.get();
        Task<String> second = scheduler.get();
        assertEquals(first.getTask(), firstTask.getTask());
        assertEquals(second.getTask(), second.getTask());
    }

    @Test(timeout=5000L)
    public void scheduleTaskInFuture() throws Exception {
        final long current = System.currentTimeMillis();
        final long future = current + 2000;
        final Task<String> task = new Task<String>("future", future);
        scheduler.schedule(task);
        scheduler.get();
        assertTrue(System.currentTimeMillis() >= future);
    }

    @Test
    public void test() throws Exception {
        assertTrue(true);
    }
}