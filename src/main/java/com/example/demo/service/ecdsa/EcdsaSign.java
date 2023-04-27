package com.example.demo.service.ecdsa;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;
import java.io.*;

public class EcdsaSign {

    public static void usage() {
        System.err.println("Usage: Sign UserID input");
        System.exit(1);
    }

    public static void sign(String args[]) throws Exception {

        String UserID = args[0];
        String input = args[1];
        String sig = input + ".sig";

        // get signer EC private key
        String fname = UserID + ".private";
        File file = new File(fname);
        int len = (int) file.length();
        FileInputStream fis = new FileInputStream(file);
        byte b[] = new byte[len];
        int nbytes = fis.read(b);
        fis.close();
        if (nbytes != len) throw new Exception("bad read: " + fname);
        PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(b);
        KeyFactory kf = KeyFactory.getInstance("EC");
        PrivateKey priv = kf.generatePrivate(pkcs8);

        // initialize signature
        Signature s = Signature.getInstance("SHA256withECDSA");

        s.initSign(priv);

        // read message, create signature
        byte msg[] = new byte[4096];

        FileInputStream in = new FileInputStream(input);

        System.err.println("Signing " + input + " using " + fname);

        while ((nbytes = in.read(msg)) > 0)
            s.update(msg, 0, nbytes);

        in.close();

        // save the signature
        FileOutputStream out = new FileOutputStream(sig);
        out.write(s.sign());
        out.close();
    }

}
