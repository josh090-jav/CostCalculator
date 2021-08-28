package com.oladad;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LFormat implements Serializable {
    private JPanel panel1;
    private JList<LFormatJob> list1;
    private JTextField heightField;
    private JTextField widthField;
    private JTextField copyField;
    private JLabel paidLabel;
    private JTextField payField;
    private JButton addButton;
    private JButton printButton;
    private JPanel payPanel;
    private JLabel iDTotalCost;
    private JPanel keyIdFields;
    private JPanel textPanelFields;
    private JPanel listPanel;
    private JPanel controlButtons;
    private JRadioButton flex;
    private JRadioButton sav;
    private JRadioButton windowGraphics;
    private JRadioButton reflecFlex;
    private JRadioButton reflecSav;
    private JButton saveButton;
    private JLabel topNameLabel;
    private JLabel namefield;
    private JButton removeButton;
    private JLabel balanceLabel;
    private ButtonGroup buttongroup1;
    int count = 0;
    String button;
    DefaultListModel l1;
    HomePage home;
    String custName;
    String custPhone;
    public ArrayList<LFormatJob> getCopiedList() {
        return copiedList;
    }
    private ArrayList<LFormatJob> copiedList;
    JFrame frame;
    private static final long serialVersionUID = 6543217861787l;


    LFormat(String custName, String custPhone){
        this.custName = custName;
        this.custPhone = custPhone;
        flex.setActionCommand("flex");
        this.sav.setActionCommand("sav");
        this.windowGraphics.setActionCommand("windowgraphics");
        this.reflecFlex.setActionCommand("reflecflex");
        this.reflecSav.setActionCommand("reflecsav");
        frame = new JFrame("Large Format Interface");
        frame.setVisible(true);
        frame.setContentPane(panel1);
        //frame.setLocation(350, 180);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        l1 = new DefaultListModel();list1.setModel(l1);
        copiedList = new ArrayList<LFormatJob>();
        namefield.setText(custName);
        setLookAndFeel();

        frame.pack();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addJobs();
            }
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int num = list1.getSelectedIndex();
                if(num >= 0) {
                    LFormatJob l = copiedList.get(num);
                    heightField.setText(String.valueOf(l.getHeight()));
                    widthField.setText(String.valueOf(l.getWidth()));
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    removeJob();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                /*I have to put a try catch around this to display an optionpane to announce, nothing
                * being selected*/
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //The plan here is to make this action button call a
                //method in the homepage to deserialize data onto the jlist
            }
        });

        payField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    setBalanceLabel();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(frame, "Just press Enter");
                }

            }
        });
    }


    void addJobs() {


        String c = heightField.getText();

        String d = widthField.getText();

        int ee = Integer.parseInt(copyField.getText());

        String name = custName;
        String phone = custPhone;
        button = selection();
        try {
            if (isSelected()) {
                //System.out.println("there is a selection");
                int num = list1.getSelectedIndex();
                copiedList.get(num).setCopy(Integer.parseInt(copyField.getText()));

                copiedList.get(num).setWidth(widthField.getText());

                copiedList.get(num).setHeight(heightField.getText());

                copiedList.get(num).setButton(selection());

                try {
                    refreshList();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                backToNull();
            } else {
                copiedList.add(new LFormatJob(c, d, ee, name, phone, button));
                try {
                    refreshList();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                backToNull();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            JOptionPane.showMessageDialog(frame, "For obtaining balance, press Enter!");
        }
    }
    void refreshList() throws IOException {
        int totalCost = 0;
        l1.removeAllElements();
        for(LFormatJob j : copiedList) {
            if(j.getCopy() == 0) {
                j.setCopy(1);
            }
            l1.addElement(j.getHeight() + " by " + j.getWidth() + " " + j.getButton() + " #" + j.calc());
            totalCost += j.calc();
        }
        iDTotalCost.setText(totalCost + "");
        ++count;
        setBalanceLabel();
        serializeData(copiedList);
    }
    String selection() {
        String button = "";
        if(flex.isSelected()) {
            button = "flex";
        } else if(sav.isSelected()) {
            button = "sav";
        } else if(windowGraphics.isSelected()) {
            button = "windowgraphics";
        } else if(reflecSav.isSelected()) {
            button = "reflecsav";
        } else if(reflecFlex.isSelected()) {
            button = "reflecflex";
        }
        return button;
    }

    void backToNull() {
        heightField.setText("");
        widthField.setText("");
        copyField.setText("1");
    }

    boolean isSelected() {
        boolean one = !list1.isSelectionEmpty();
        return one;
    }

    void removeJob() throws IOException {
        if(isSelected()) {
            System.out.println("there is a selection");
            int num = list1.getSelectedIndex();
            copiedList.remove(num);
            refreshList();
            backToNull();
        }
    }

    public void serializeData(ArrayList list) throws IOException {
        FileOutputStream file = new FileOutputStream("outData.ser");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(list);
        out.close();
        file.close();
    }

    public void setBalanceLabel() {
        int a = Integer.parseInt(payField.getText());
        int b = Integer.parseInt(iDTotalCost.getText());
        int c = b - a;
        balanceLabel.setText("" + c);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("Javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc) {

        }
    }
}
