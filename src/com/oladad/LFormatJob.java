package com.oladad;

/*
For this to be truly usable, there must be a Class to set the prices
of the various forms of printing.
* */

import java.io.Serializable;
import java.util.Locale;

public class LFormatJob implements Calculator, Serializable {

    private String height;
    private String width;
    private int copy;
    private static final long serialVersionUID = 12345678910l;


    /*public JRadioButton getButton() {
        return button;
    }*/

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    private String button;

    public LFormatJob(String height, String width, int copy, String name, String phone, String button) {
        this.height = height;
        this.width = width;
        this.copy = copy;
        this.name = name;
        this.number = phone;
        this.button = button;
    }

    public LFormatJob(){}

    private String name;
    private String number;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int calc() {
        double rad = 0;
        try {
            String radio = button.toLowerCase(Locale.ROOT);
            double a = Double.parseDouble(getHeight());
            double b = Double.parseDouble(getWidth());
            rad = switch (radio) {
                case "sav", "flex" -> a * b * getCopy() * 100;
                case "windowgraphics" -> a * b * getCopy() * 200;
                case "reflecflex" -> a * b * getCopy() * 300;
                case "reflecsav" -> a * b * getCopy() * 280;
                default -> 0;
            };

        } catch (NumberFormatException a) {
            a.printStackTrace();
            ;
        }
        return (int) rad;
    }

   // I might have to delete this overloaded method
   // I can convert type within the already given method

    public String toString(){
        return "Height is " + getHeight();
    }
}
