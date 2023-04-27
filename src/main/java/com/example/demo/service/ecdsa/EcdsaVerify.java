package com.example.demo.service.ecdsa;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;
import java.io.*;

public class EcdsaVerify {

    public static void usage() {
        System.err.println("Usage: Verify UserID input");
        System.exit(1);
    }

    public static void verify(String args[]) throws Exception {

        // input file names
        String UserID = args[0];
        String input = args[1];
        String sig = input + ".sig";

        BigInteger WX, WY;

        // get signer EC public key
        String fname = UserID + ".public";
        File file = new File(fname);
        int len = (int) file.length();
        FileInputStream fis = new FileInputStream(file);
        byte b[] = new byte[len];
        int nbytes = fis.read(b);
        fis.close();
        if (nbytes != len) throw new Exception("bad read: " + fname);

        X509EncodedKeySpec x509 = new X509EncodedKeySpec(b);
        KeyFactory kf = KeyFactory.getInstance("EC");
        PublicKey pub = kf.generatePublic(x509);

        // initialize signature
        Signature s = Signature.getInstance("SHA256withECDSA");

        s.initVerify(pub);

        // read message
        byte msg[] = new byte[4096];

        FileInputStream in = new FileInputStream(input);

        System.err.println("Verifying " + input + " using " + fname);

        while ((nbytes = in.read(msg)) > 0)
            s.update(msg, 0, nbytes);

        in.close();

        // check signature
        file = new File(sig);
        len = (int) file.length();
        fis = new FileInputStream(file);
        b = new byte[len];
        nbytes = fis.read(b);
        fis.close();
        if (nbytes != len) throw new Exception("bad read: " + sig);

        if (s.verify(b))
            System.err.println("Signature is valid.");
        else
            System.err.println("INVALID signature.");
    }

}
