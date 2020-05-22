package com.cksolutions.finalapp.model;

public class ColorModel {
    private int red;
    private int green;
    private int blue;
    private String name;

    public ColorModel(int red, int green, int blue, String name) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
