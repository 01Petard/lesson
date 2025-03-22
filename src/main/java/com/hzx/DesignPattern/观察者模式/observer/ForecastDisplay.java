package com.hzx.DesignPattern.观察者模式.observer;

public class ForecastDisplay implements BaseObserver {
    @Override
    public void update(String weatherInfo) {
        System.out.println("Forecast Display: " + weatherInfo);
    }
}
