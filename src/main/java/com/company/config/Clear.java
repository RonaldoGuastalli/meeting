package com.company.config;

import java.io.IOException;

public class Clear {

    public static void screen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                try {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } catch (InterruptedException e) {
                    // Restore interrupted state...
                    Thread.currentThread().interrupt();
                }
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException ex) {
        }
    }
}
