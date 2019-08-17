package com.designpattern.decorate;

public class CarTest {
    public static void main(String[] args) {
        Car myCar = new MyCar();
        ReadCar readCar = new ReadCar(myCar);
        AudiCar audiCar = new AudiCar(readCar);

        System.err.println(audiCar.getDescription());;
    }
}
