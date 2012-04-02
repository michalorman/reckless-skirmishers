package com.domain.reckless.core.controls;

import java.util.LinkedList;
import java.util.List;

public abstract class EventQueue<E> {
    private List<E> events = new LinkedList<>();
    private List<E> eventQueue = new LinkedList<>();

    public synchronized void update() {
        for (E event : events) {
            processEvent(event);
        }
        events = eventQueue;
        eventQueue = new LinkedList<>();
        System.out.println(events.size());
    }

    protected abstract void processEvent(E event);

    protected synchronized void addEvent(E event) {
        eventQueue.add(event);
    }

}
