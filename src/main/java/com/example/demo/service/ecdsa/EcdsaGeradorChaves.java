package com.example.demo.service.ecdsa;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;

public class EcdsaGeradorChaves {

    static String name = "secp521r1"; // "secp256r1";

    public static void usage() {
        System.err.println("Usage: ECgen [-c curve_name (default: secp256k1)] UserID");
        System.exit(1);
    }

    public static void gerarChavesArquivo(String args[]) throws Exception {

        String UserID = args[0];

        // generate key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec curve = new ECGenParameterSpec(name);
        kpg.initialize(curve);
        KeyPair kp = kpg.genKeyPair();
        ECPrivateKey priv = (ECPrivateKey) kp.getPrivate();
        ECPublicKey pub = (ECPublicKey) kp.getPublic();

        // save private key
        FileOutputStream fos = new FileOutputStream(UserID + ".private");
        fos.write(priv.getEncoded());
        fos.close();

        System.out.println("\nPRIVATE");
        System.out.println(priv.getEncoded().toString());
        System.out.println(priv.getAlgorithm());
        System.out.println(priv.getS());

        System.out.println("\nPUBLIC");
        System.out.println(pub.getEncoded().toString());
        System.out.println(pub.getAlgorithm());
        System.out.println(pub.getW());

        // save public key
        //
        fos = new FileOutputStream(UserID + ".public");
        fos.write(pub.getEncoded());
        fos.close();

        // display private and public values
        BigInteger S = priv.getS();
        ECPoint W = pub.getW();
        BigInteger WX = W.getAffineX();
        BigInteger WY = W.getAffineY();

        System.out.println(name);
        System.out.println("  S = " + S.toString(16));
        System.out.println("W.X = " + WX.toString(16));
        System.out.println("W.Y = " + WY.toString(16));
    }

}
