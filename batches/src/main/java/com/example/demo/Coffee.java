package com.example.demo;

public class Coffee extends Object {

    private String brand;
    private String origin;
    private String characteristics;

    public Coffee() {
    }

    public Coffee(String brand, String origin, String characteristics) {
        this.brand = brand;
        this.origin = origin;
        this.characteristics = characteristics;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCharacteristics() {
        return this.characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "brand='" + brand + '\'' +
                ", origin='" + origin + '\'' +
                ", characteristics='" + characteristics + '\'' +
                '}';
    }
}
