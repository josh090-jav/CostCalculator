package com.oladad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Flow;


public class NameDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public JTextField getNameField1() {
        return nameField1;
    }

    public JTextField getPhoneField2() {
        return phoneField2;
    }

    private JTextField nameField1;
    private JTextField phoneField2;

    public NameDialog() {
        setContentPane(contentPane);
        setModal(true);
        //add(dialog);
        setVisible(true);
        pack();
        //setLayout(new FlowLayout());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

    }

    private void onOK() {
        // add your code here
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
