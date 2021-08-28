package com.oladad;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class HomePage {

    private JPanel paneMain;
    private JList <LFormatJob> homeList;
    private JButton lFormatButton;
    private JButton a3DPrintButton;
    private JPanel countPane;
    private JLabel lformatLabel;
    private JLabel _3dLabel;
    private JLabel showLCount;
    private JLabel show3Dcount;
    private JButton refreshButton;
    private JButton editButton;
    private NameDialog dialog;
    private static final long serialVersionUID = 10987654321l;

    public DefaultListModel getModel() {
        return model;
    }

    private final DefaultListModel model;


    private ArrayList<LFormatJob>list1;
    JFrame frame;

    public void setDone(boolean done) {
        this.done = done;
    }

    private boolean done;


    public HomePage() {
        frame = new JFrame("Home");
        frame.setVisible(true);
        frame.setContentPane(paneMain);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(550, 180);
        frame.pack();
        model = new DefaultListModel();
        homeList.setModel(model);
        list1 = new ArrayList<>();

        try {
            list1 = deserialized();
        } catch (Exception e) {
            e.printStackTrace();
        }

        lFormatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OptionPane("LFormat");
            }
        });


        a3DPrintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new SubGui();
                new OptionPane("3D");
            }
        });
        homeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //getModel().addElement("I am frustrated for real");
                //System.out.println("But is the arraylist empty outside the thread? " + isNotNull());
                try {
                    printToScreen(list1);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

    }

    void printToScreen(ArrayList<LFormatJob> list) throws Exception {

        list = deserialized();

       getModel().removeAllElements();

        for (LFormatJob j : list) {

            //System.out.println("the value of J is " + j.getWidth());
            list1.add(j);
            //System.out.println("this says arraylist of home data is empty, true or false " + isNotNull());
            getModel().addElement(j.getHeight() + " by " + j.getWidth() + " " + j.getButton() + " #" + j.calc());
        }
    }

    boolean isNotNull() {
        return list1.isEmpty();
    }

    public ArrayList<LFormatJob> deserialized() throws Exception{
        ArrayList<LFormatJob> list;
        FileInputStream file = new FileInputStream("outData.ser");
        ObjectInputStream in = new ObjectInputStream(file);
        list = (ArrayList) in.readObject();
        in.close();
        file.close();
        return list;
    }

}
