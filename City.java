package com.example.tqpc.bean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@Component
public class City {
    public static ArrayList<String> getCityList(){
        ArrayList<String> list = new ArrayList<>();
        Elements elements = null;
        try {
            elements = Jsoup.connect("http://lishi.tianqi.com/#city-L").get().select("[class=table_list]").select("a");


        }catch (Exception e){

        }
        for (Element element : elements) {
            if (element.attr("href").split("/")[0].equals("")){

            }else {
                list.add(element.attr("href").split("/")[0]);
            }
        }
        return list;
    }
    public static Map<String, String> getCityMap(){
        Map<String,String> map = new TreeMap<>();

        Elements elements = null;
        try {
            elements = Jsoup.connect("http://lishi.tianqi.com/#city-L").get().select("[class=table_list]").select("a");


        }catch (Exception e){
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement element1 : stackTrace) {
                System.out.println("城市出现的问题是:"+element1);
            }
        }
        for (Element element : elements) {
            if (element.attr("href").split("/")[0].equals("")) {

            } else {
                map.put(element.attr("href").split("/")[0],element.text());
            }
        }
//           System.out.println(element.attr("href").split("/")[0]+" "+element.text());

        return map;
    }
}
