package com.vidor;

import org.springframework.stereotype.Component;

@Component
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("run");
    }
}
