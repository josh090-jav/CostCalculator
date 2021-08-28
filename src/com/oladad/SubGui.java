package com.oladad;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class SubGui {
    private JTextField paidField;
    private JPanel paidPanel;
    private JPanel radioChoices;
    private JRadioButton SEWINGRadioButton;
    private JRadioButton longSleeveRadioButton;
    private JRadioButton shortSleeveRadioButton;
    private JLabel paidLabel;
    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton printButton;
    private JTextField heightField;
    private JTextField widthField;
    private JTextField copyField;
    private JTextField ageField;
    private JList subList;
    private JPanel mainPanel;
    private JRadioButton paperOnlyRadioButton;
    private JLabel iDTotalCost;
    DefaultListModel listOne;
    ArrayList<SubJob> jobs;
    //SubJob one;
    String number;
    String name;
    JFrame frame;
    public SubGui(String name, String number) {
        this.name = name;
        this.number = number;
        frame = new JFrame("Sublimation");
        frame.setVisible(true);
        frame.setContentPane(mainPanel);
        frame.setLocation(150, 80);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        listOne = new DefaultListModel(); subList.setModel(listOne);
        jobs = new ArrayList<>();

        shortSleeveRadioButton.setEnabled(false);
        longSleeveRadioButton.setEnabled(false);

        SEWINGRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SEWINGRadioButton.isSelected()) {
                    longSleeveRadioButton.setEnabled(true);
                    shortSleeveRadioButton.setEnabled(true);
                } else {
                    shortSleeveRadioButton.setEnabled(false);
                    longSleeveRadioButton.setEnabled(false);
                }
            }
        });

        paperOnlyRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SEWINGRadioButton.setEnabled(!paperOnlyRadioButton.isSelected());
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addJobs();
            }
        });
        subList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int num = subList.getSelectedIndex();
                if(num >= 0) {
                    SubJob l = jobs.get(num);
                    heightField.setText(String.valueOf(l.getHeight()));
                    widthField.setText(String.valueOf(l.getWidth()));
                    copyField.setText(String.valueOf(l.getCopy()));
                    ageField.setText(String.valueOf(l.getAge()));
                    radioSelection();
                }

            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    removeJob();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void addJobs(){
        String a = name;
        String b = number;
        String c = heightField.getText();
        String d = widthField.getText();
        int copy = Integer.parseInt(copyField.getText());
        String age = ageField.getText();
        int paid = Integer.parseInt(paidField.getText());
        String e = radioSelection();
        if(isSelected()) {
            int num = subList.getSelectedIndex();
            jobs.get(num).setHeight(heightField.getText());
            jobs.get(num).setWidth(widthField.getText());
            jobs.get(num).setCopy(Integer.parseInt(copyField.getText()));
            jobs.get(num).setAge(ageField.getText());
            try {
                refreshList();
            } catch (IOException f) {
                f.printStackTrace();
            }
        } else {
            jobs.add(new SubJob(a, b, c, d, copy, age, paid, e));
            try {
                refreshList();
            } catch (IOException f) {
                f.printStackTrace();
            }
        }
        backToNull();
    }

    private String radioSelection() {
        String selection;
        if(SEWINGRadioButton.isSelected()) {
            if(longSleeveRadioButton.isSelected()) {
                selection = "LS";
            } else {
                selection = "SL";
            }
        } else if(paperOnlyRadioButton.isSelected()) {
            selection = "paper";
        } else {
            selection = "none";
        }
        return selection;
    }

    void refreshList() throws IOException {
        int totalCost = 0;
        listOne.removeAllElements();
        for(SubJob j : jobs) {
            if(j.getCopy() == 0) {
                j.setCopy(1);
            }
            listOne.addElement(j.getHeight() + " by " + j.getWidth() + " " + j.getRadioButton() + " #" + j.calc());
            totalCost += j.calc();
        }
        iDTotalCost.setText(totalCost + "");
        //++count;
        //serializeData(copiedList);
    }

    void backToNull() {
        heightField.setText("");
        widthField.setText("");
        copyField.setText("1");
        ageField.setText("");
        paidField.setText("0");
    }

    boolean isSelected() {
        return !subList.isSelectionEmpty();
    }

    void removeJob() throws IOException {
        if(isSelected()) {
            System.out.println("there is a selection");
            int num = subList.getSelectedIndex();
            jobs.remove(num);
            refreshList();
            backToNull();
        }
    }

}
