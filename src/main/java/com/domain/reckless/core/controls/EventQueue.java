package com.domain.reckless.core.controls;

import java.util.LinkedList;
import java.util.List;

public abstract class EventQueue<E> {
    private List<E> events = new LinkedList<>();
    private List<E> eventQueue = new LinkedList<>();

    protected synchronized void update() {
        for (E event : events) {
            processEvent(event);
        }
        events = eventQueue;
        eventQueue = new LinkedList<>();
    }

    protected abstract void processEvent(E event);

    protected synchronized void addEvent(E event) {
        eventQueue.add(event);
    }

}
