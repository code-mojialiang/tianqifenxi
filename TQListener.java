package com.example.wotule.controller;

import com.example.wotule.utils.reUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.FileWriter;
@Component
public class TQListener {

    private static BufferedWriter bufferedWriter =null;
    static {
        try {
            bufferedWriter=new BufferedWriter(new FileWriter("/home/hadoop/data/tianqi.log",true));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @KafkaListener(topics = "tianqi-log")
    public void onMessage(String message){
       reUtils.savelog(bufferedWriter,message);
        System.out.println(message);



    }
}
