package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrackerSingleGetTest {

    @Test
    public void getInstance() {
        Tracker tracker = TrackerSingleGet.getInstance();
        Tracker tracker2 = TrackerSingleGet.getInstance();
        assertTrue(tracker.equals(tracker2));
    }
}