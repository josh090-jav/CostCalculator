package com.oladad;

import javax.swing.*;

public class OptionPane {

    //Object[]objects;
    JLabel nameLabel = new JLabel("Enter Name");
    JTextField name = new JTextField(10);
    JLabel phoneLabel = new JLabel("Enter Phone Number");
    JTextField phone = new JTextField(10);
    JPanel pane1 = new JPanel();
    String type;

    OptionPane(String type) {
        this.type = type;
        pane1.setLayout(new BoxLayout(pane1, BoxLayout.PAGE_AXIS));
        pane1.add(nameLabel);
        pane1.add(name);
        pane1.add(phoneLabel);
        pane1.add(phone);
        if("LFormat".equals(type)) {
            //JOptionPane.showInputDialog(null, pane1, "Input Customer's Name", JOptionPane.OK_CANCEL_OPTION);
            int s = JOptionPane.showConfirmDialog(null, pane1, "Enter Details", JOptionPane.OK_CANCEL_OPTION);
            //new LFormat();
            if (!(name.getText().equals("")) && s == JOptionPane.YES_OPTION) {
                new LFormat(name.getText(), phone.getText());
            } else if (JOptionPane.NO_OPTION == s) {
                JOptionPane.showMessageDialog(null, "No input recorded");
            }
        } else if("3D".equals(type)) {
            //JOptionPane.showInputDialog(null, pane1, "Input Customer's Name", JOptionPane.OK_CANCEL_OPTION);
            int s = JOptionPane.showConfirmDialog(null, pane1, "Enter Details", JOptionPane.OK_CANCEL_OPTION);
            //new LFormat();
            if (!(name.getText().equals("")) && s == JOptionPane.YES_OPTION) {
                new SubGui(name.getText(), phone.getText());
            } else if (JOptionPane.NO_OPTION == s) {
                JOptionPane.showMessageDialog(null, "No input recorded");
            }
        }
    }

}
