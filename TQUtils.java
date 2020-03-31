package com.example.tqpc.utils;


import com.example.tqpc.bean.City;
import com.example.tqpc.bean.Weather;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class TQUtils {
    private static int XCnum = 10;
//    private  static  KafkaTemplate<String, String> kafkaTemplate;
//
//    public static void qidong() {
////        Elements elements = Weather.getElements();
////        for (Element element : elements) {
////            System.out.println(element);
////        }
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//
//                CityURL(0, 10,kafkaTemplate);
//
//            }, String.valueOf(i)).start();
//        }
//    }

//    private static BufferedWriter bufferedWriter = null;
//
//    static {
//        try {
//            bufferedWriter = new BufferedWriter(new FileWriter("e:/text.txt", true));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static void CityURL(int num, int XCnum, KafkaTemplate<String, String> kafkaTemplate) {
        int a = Integer.parseInt(Thread.currentThread().getName());
        Map<String, String> map = City.getCityMap();
        ArrayList<String> list = City.getCityList();
        ArrayList<String> list1 = new ArrayList<String>();
        Elements elements = null;
        Elements elements1 = null;


        for (int i = (list.size() / XCnum) * a; i < (((list.size() / XCnum) * (a + 1))); i++) {

            for (int m = 2011; m < 2019; m++) {
                try {
                    for (int j = 1; j < 13; j++) {
                        if (j < 10) {
                            list1.add("http://lishi.tianqi.com/" + list.get(i) + "/" + m + "0" + j + ".html");
                        } else {
                            list1.add("http://lishi.tianqi.com/" + list.get(i) + "/" + m + j + ".html");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        for (int i = 0; i < list1.size(); i++) {
            String ss = map.get(list1.get(i).split("/")[3].toString());
            try {

                elements1 = Jsoup.connect(list1.get(i)).get().select("[class=lishitable_content clearfix]").select("li").attr("class", "");

                for (int j = 0; j < elements1.size() - 1; j++) {
                    Weather weather = new Weather();
                    weather.setCity(ss);
                    weather.setDate(elements1.get(j).select("div").get(0).text());
                    weather.setMaxQW(elements1.get(j).select("div").get(1).text());
                    weather.setMinQW(elements1.get(j).select("div").get(2).text());
                    weather.setTianQi(elements1.get(j).select("div").get(3).text());
                    weather.setFengXiang(elements1.get(j).select("div").get(4).text());
                    kafkaTemplate.send("tianqi-log", weather.toString());

                }
            } catch (Exception e) {
                StackTraceElement[] stackTrace = e.getStackTrace();
                for (StackTraceElement element1 : stackTrace) {
                    System.out.println(Thread.currentThread().getName() + "号线程爬取的" + ss + "城市出现的问题是:" + element1);
                }

            }

        }

    }

}
