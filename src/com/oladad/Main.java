package com.oladad;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        setLookAndFeel();

        HomePage one = new HomePage();
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("Javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc) {

        }
    }
}
