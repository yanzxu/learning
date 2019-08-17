package com.designpattern.decorate;

public class AudiCar extends CarDecorate {
    private Car car;

    public AudiCar(Car car){
        this.car = car;
    }

    @Override
    public String getDescription() {
        return car.getDescription() + " Brand: audi";
    }
}
