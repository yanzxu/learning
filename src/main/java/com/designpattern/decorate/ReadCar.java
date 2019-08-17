package com.designpattern.decorate;

public class ReadCar extends CarDecorate {
    private Car car;
    public ReadCar(Car car){
        this.car = car;
    }
    @Override
    public String getDescription() {
        return car.getDescription() + " Color: Red";
    }
}
