package com.qacart;

import java.time.Instant;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        long timeStamp = Instant.now().getEpochSecond();
        System.out.println("Time Stamp: " + timeStamp);
    }
}