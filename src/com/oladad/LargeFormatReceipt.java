package com.oladad;

import java.awt.Graphics;
import java.awt.print.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class LargeFormatReceipt implements Printable, ActionListener, Serializable {

    ArrayList <LFormatJob> list;

    public LargeFormatReceipt(ArrayList<LFormatJob> list) {
        this.list = list;
    }


    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        // TODO Auto-generated method stub


        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2g = (Graphics2D) graphics;
        g2g.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        //g2g.setFont(new Font("Monospaced", Font.PLAIN, 10));
        pageFormat.setOrientation(PageFormat.PORTRAIT);
        Paper paper = new Paper();

        // Find the optimal size for pos printing.

        paper.setSize(0.05, 1);
        paper.setImageableArea(0.1, 0.1, paper.getWidth(), paper.getHeight());
        pageFormat.setPaper(paper);

        int yShift = 20;
        int y = 10;

        graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        graphics.drawString("****************************************", 100, y);
        y += yShift;
        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        graphics.drawString("Oladad Graphics and Print", 117, y - 15);
        y += yShift;
        graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        graphics.drawString("****************************************", 100, y - 23);
        y += yShift - 30;
        graphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
        graphics.drawString("Customer: " + list.get(0).getName() + "\n", 100, y);
        y += yShift - 10;
        graphics.drawString("Phone: " + list.get(0).getNumber() + "\n", 100, y);
        y += yShift - 10;
        String ref = null;
        try {
            ReceiptNum num = new ReceiptNum();
            ref = "" + num.sendRef();
            num.serializeClass();
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics.drawString("Receipt Ref: " + 1 + "\n", 100, y);
        y += yShift;
        graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        graphics.drawString("****************************************", 100, y);
        graphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        y += yShift - 12;
        //graphics.drawString("\n", 100, y);y += yShift;


        //g2g.setFont(new Font("San Serif", Font.ROMAN_BASELINE, 8));

        for (int a = 0; a < list.size(); ++a) {

            String one = list.get(a).getHeight();
            String two = list.get(a).getWidth();
            String three = list.get(a).getButton();
            String four = list.get(a).getCopy() + "";

            graphics.drawString(one + " by " + two + " " + three + " (Copy: " + four + ")", 100, y);
            y += yShift;
        }

        graphics.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 8));

        graphics.drawString("\n" + "Thank you for coming !!!" + "\n", 135, y);
        y += yShift - 12;

        //g2g.setFont(new Font("Arial",Font.BOLD,3));
        graphics.drawString("Software by Josh.Olatunji", 135, y);
        //y += yShift;


        return PAGE_EXISTS;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                /* The job did not successfully complete */
            }
        }


    }

}
