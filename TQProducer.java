package com.example.tqpc.controller;

import com.example.tqpc.utils.TQUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TQProducer {
    //kafka的模板工具类
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;


    @RequestMapping("/weatherLog")
    @ResponseBody

    public Map<String, String> initLog() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {

                TQUtils.CityURL(0, 10,kafkaTemplate);

            }, String.valueOf(i)).start();
        }

        Map<String, String> map = new HashMap<>();
        map.put("message", "ok");
        return map;
    }
}
