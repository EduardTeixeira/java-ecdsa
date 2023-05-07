package com.example.demo.service.ecdsa;

import java.security.*;
import java.security.spec.*;
import java.io.*;

public class EcdsaVerify {

    public static void verify(String publicKeyLocal, String assinatura, String inputFile) throws Exception {

        String input = inputFile + ".sig";
        String sig = inputFile + ".sig";

        // get signer EC public key
        String fname = publicKeyLocal + ".public";
        File file = new File(fname);
        int len = (int) file.length();
        FileInputStream fis = new FileInputStream(file);

        byte b[] = new byte[len];
        int nbytes = fis.read(b);

        fis.close();

        if (nbytes != len) throw new Exception("bad read: " + fname);

        KeyFactory kf = KeyFactory.getInstance("EC");
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(b);
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
