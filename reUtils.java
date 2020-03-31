package com.example.wotule.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;

@Component
public class reUtils {
    public static void savelog(BufferedWriter bf, String message){
        try {
            bf.write(message);
            bf.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
