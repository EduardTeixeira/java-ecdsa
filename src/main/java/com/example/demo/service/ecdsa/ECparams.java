package com.example.demo.service.ecdsa;

import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;
import java.io.*;

public class ECparams {

    public static void usage()
    {
        System.err.println( "Usage: ECparams UserID");
        System.exit(1);
    }

    public static void main(String args[]) throws Exception
    {
        String UserID = null;

        if( args.length != 1)
            usage();

        UserID = args[0];

        // get EC public key
        //
        String fname = UserID + ".public";
        File file = new File( fname);
        int len = (int) file.length();
        FileInputStream fis = new FileInputStream( file);
        byte b[] = new byte[len];
        int nbytes = fis.read( b);
        fis.close();
        if( nbytes != len) throw new Exception( "bad read: " + fname);

        X509EncodedKeySpec x509 = new X509EncodedKeySpec( b);
        KeyFactory kf = KeyFactory.getInstance( "EC");
        ECPublicKey pub = (ECPublicKey) kf.generatePublic( x509);

        // extract parameters
        //
        ECParameterSpec params = pub.getParams();
        EllipticCurve c = params.getCurve();
        ECPoint G = params.getGenerator();
        BigInteger n = params.getOrder();
        int h = params.getCofactor();

        BigInteger A = c.getA();
        BigInteger B = c.getB();

        BigInteger GX = G.getAffineX();
        BigInteger GY = G.getAffineY();

        ECField f = c.getField();
        String type;
        BigInteger P = null;		// ECFieldFp
        BigInteger poly = null; int m = 0;	// ECFieldF2m

        if( f instanceof ECFieldFp)
        {
            type = "ECFieldFp";
            ECFieldFp fp = (ECFieldFp) f;
            P = fp.getP();
        }
        else
        {
            type = "ECFieldF2m";
            ECFieldF2m f2m = (ECFieldF2m) f;
            m = f2m.getM();
            poly = f2m.getReductionPolynomial();
            if( poly == null) poly = BigInteger.ZERO;
        }

        System.out.println( type);
        System.out.println( "G.X = " + GX.toString(16));
        System.out.println( "G.Y = " + GY.toString(16));
        System.out.println( "  A = " + A.toString(16));
        System.out.println( "  B = " + B.toString(16));
        System.out.println( "  n = " + n.toString(16));
        System.out.println( "  h = " + h);

        switch( type)
        {
            case "ECFieldFp":
                System.out.println( "  P = " + P.toString(16));
                break;
            case "ECFieldF2m":
                System.out.println( "  m = " + m);
                System.out.println( "poly= " + poly.toString(16));
                break;
        }
    }

}
