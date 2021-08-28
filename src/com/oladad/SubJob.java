package com.oladad;


import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

public class SubJob implements Calculator, Serializable {

    @Serial
    private static final long serialVersionUID = 787865432187L;
    private String name;
    private String number;
    private String height;
    private String width;
    //private String matButton;

    public String getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(String radioButton) {
        this.radioButton = radioButton;
    }

    private String radioButton;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    private int copy;
    private String age;
    private int paid;
    //HashMap<String, Integer> sizeCost;
    // I have to create a class that takes input of cost, instantiate that class here
    // receive its size input method
    /*******************************************************************************/
    // I should have a class for setting the prices of things. An Object of such a class to
    // be declared here can have its members gotten.

    public SubJob(String name, String number, String height, String width, int copy,
                  String age, int paid, String radioButton) {
        this.name = name;
        this.number = number;
        this.height = height;
        this.width = width;
        this.copy = copy;
        this.paid = paid;
        this.age = age;
        this.radioButton = radioButton;

    }

    @Override
    public int calc() {
        String [] costTypes = new String[2];
        costTypes = convertSizeToAge(getHeight(), getWidth(), getAge()).split(",");
        int cost;
        switch (getRadioButton()) {
            case "paper":
                cost = Integer.parseInt(costTypes[0]) * getCopy();
                break;
            case "SL":
                cost = Integer.parseInt(costTypes[1]) * getCopy();
                break;
            case "LS":
                cost = Integer.parseInt(costTypes[1]) + 500 * getCopy();
                break;
            case "none":
                cost = noMat(getHeight(), getWidth()) * getCopy();
            default:
                cost = noMat(getHeight(), getWidth()) * getCopy();
        }
        return cost;
    }

    public String convertSizeToAge(String height, String width, String age) {
        //
        //
        String one = "";
        double h = Double.parseDouble(height);
        double w = Double.parseDouble(width);
        double tt = h + w;
        double xx = h * w;

            if ((tt >= 0 && tt <= 26) || "1".equals(age)) {
                one = "800,1700";
            } else if ((tt >= 27 && tt <= 31) || "2".equals(age)) {
                one = "800,1800";
            } else if ((tt >= 32 && tt <= 33) || ("3".equals(age) || "4".equals(age))) {
                one = "900,1800";
            } else if ((tt >= 34 && tt <= 35) || ("5".equals(age) || "6".equals(age))) {
                one = "1000,1900";
            } else if ((tt >= 36 && tt <= 39) || ("7".equals(age) || "8".equals(age))) {
                one = "1100,2000";
            } else if ((tt >= 40 && tt <= 41) || ("9".equals(age) || "10".equals(age))) {
                one = "1150,2050";
            } else if ((tt >= 42 && tt <= 43.5) || ("11".equals(age) || "12".equals(age))) {
                one = "1200,2200";
            } else if ((tt >= 43.6 && tt <= 45.5) || ("13".equals(age) || "14".equals(age))) {
                one = "1250,2250";
            } else if (tt >= 45.6 && tt <= 47) {
                one = "1250,2250";
            } else if (tt >= 48 && tt <= 50) {
                one = "1300,2400";
            } else if (tt >= 51 && tt <= 52) {
                one = "1350,2450";
            } else if (tt >= 53 && tt <= 55) {
                one = "1400,2500";
            } else if (tt >= 56 && tt <= 57) {
                one = "1550,2750";
            } else if (tt >= 58 && tt <= 59) {
                one = "2000,3300";
            } else if (tt >= 60) {
                one = xx * 1.5 + 800 + "," + xx * 1.5 + 2300;
            }
            //return one;
    return one;
    }

    public int noMat(String height, String width) {
        int mat = 0;
        double h = Double.parseDouble(height);
        double w = Double.parseDouble(width);
        double tt = h + w;
        if(tt > 0 && tt <= 10) {
            mat = 100;
        } else if(tt >= 11 && tt <= 15) {
            mat = 250;
        } else if(tt >= 16 && tt <= 20) {
            mat = 350;
        } else if (tt >= 21 && tt <= 25) {
            mat = 450;
        } else if (tt >= 26 && tt <= 32) {
            mat = 600;
        } else if (tt >= 33 && tt <= 55) {
            mat = 900;
        } else if (tt >= 56 && tt <= 58) {
            mat = 1250;
        } else if (tt >= 59 && tt <= 63) {
            mat = 1500;
        } else if (tt >= 64 && tt <= 75) {
            mat = 1700;
        } else if (tt >= 76 && tt <= 86) {
            mat = 1900;
        } else if (tt >= 87 && tt <= 95) {
            mat = 2300;
        } else if (tt >= 96 && tt <= 105) {
            mat = 2500;
        } else if (tt >= 106) {
            mat = (int) (h * w * 1.2);
        }
        return mat;
    }

    // Have another method that takes the string input in agefield and get the hashmap key of corresponding
    // string. Use enum to iterate through the hashmap

}
