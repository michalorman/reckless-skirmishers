package com.domain.reckless.game;

public class RecklessSkirmishers {
    
    public static void main(String[] args) {
        Thread thread = new Thread(new Game());
        thread.start();
    }
}
