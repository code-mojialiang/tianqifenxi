package com.example.tqpc.bean;

import org.springframework.stereotype.Component;

@Component
public class Weather {
    private String city;
    private String date;
    private String MaxQW;
    private String MinQW;
    private String TianQi;
    private String FengXiang;

    @Override
    public String toString() {
        StringBuffer sbf = new StringBuffer();
        sbf.append(city).append("---").append(date).append("---").append(MaxQW);
        sbf.append("---").append(MinQW).append("---").append(TianQi).append("---").append(FengXiang).append("\n");
        return sbf.toString();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaxQW() {
        return MaxQW;
    }

    public void setMaxQW(String maxQW) {
        MaxQW = maxQW;
    }

    public String getMinQW() {
        return MinQW;
    }

    public void setMinQW(String minQW) {
        MinQW = minQW;
    }

    public String getTianQi() {
        return TianQi;
    }

    public void setTianQi(String tianQi) {
        TianQi = tianQi;
    }

    public String getFengXiang() {
        return FengXiang;
    }

    public void setFengXiang(String fengXiang) {
        FengXiang = fengXiang;
    }
}
