package com.oladad;

import java.io.*;

public class ReceiptNum implements Serializable {

    @Serial
    private static final long serialVersionUID = 7878654024576L;

    private Long ref = 0l;
    public ReceiptNum() throws IOException {
        //ref = 0l;
        //serializeClass();
        //deserializeClass();
        setRef(deserializeClass().getRef());
    }

    public Long getRef() {
        return ref;
    }

    public void setRef(Long ref) {
        this.ref = ref;
    }

    public Long sendRef() {

        System.out.println(getRef());
        long a = getRef() + 1;
        //setRef(a);
        return a;
    }

    public void serializeClass() throws IOException {
        FileOutputStream fis = new FileOutputStream("Receipt num.ser");
        ObjectOutputStream obs = new ObjectOutputStream(fis);
        obs.writeObject(this);
        fis.close();
        obs.close();
    }

    public ReceiptNum deserializeClass() throws IOException {
        FileInputStream fes = new FileInputStream("Receipt num.ser");
        ObjectInputStream obi = new ObjectInputStream(fes);
        try {
            ReceiptNum one = (ReceiptNum) obi.readObject();
             return one;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        fes.close();
        obi.close();
        return null;
    }

}
