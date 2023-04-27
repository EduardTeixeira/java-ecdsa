package com.example.demo.service.ecdsa;

import java.security.*;
import java.security.spec.*;
import java.security.cert.*;
import java.security.interfaces.*;
import java.io.*;

public class EcdsaCertVerify {

    public static void usage()
    {
        System.err.println( "Usage: CertVerify UserID input");
        System.exit(1);
    }

    public static void main( String args[]) throws Exception
    {
        if( args.length != 2)
            usage();

        String UserID = args[0];
        String input = args[1];
        String sig = input + ".sig";

        String certfile = UserID + ".cert";
        String CAcertfile = UserID + ".CAcert";

        // read certificate and extract public key
        //
        FileInputStream fis = new FileInputStream( certfile);
        CertificateFactory cf = CertificateFactory.getInstance( "X509");
        X509Certificate cert = (X509Certificate) cf.generateCertificate( fis);
        fis.close();
        PublicKey pub = cert.getPublicKey();
        String alg = pub.getAlgorithm();

        // print some info and check validity (e.g. not expired)
        //
        System.out.println( "Certificate Subject:\n" + cert.getSubjectX500Principal());
        System.out.println( "Checking certificate validity...");
        cert.checkValidity(); // will throw an exception if not valid
        System.out.println( "Certificate is valid.");
        System.out.println( "Key type is " + alg);

        // if CAcert file exists, check the cert signature
        //
        fis = null;
        try { fis = new FileInputStream( CAcertfile); } catch( Exception ex) {}
        if( fis == null)
            System.out.println( CAcertfile +
                    " file not found, skipping certificate signature check.");
        else
        {
            X509Certificate CAcert = (X509Certificate) cf.generateCertificate( fis);
            fis.close();
            PublicKey CApub = CAcert.getPublicKey();
            System.out.println( "Checking certificate signature...");
            cert.verify( CApub); // will throw an exception if not verified
            System.out.println( "Certificate signature is verified.");
        }

        // initialize signature
        //
        Signature s = Signature.getInstance( "SHA256withECDSA");

        s.initVerify( pub);

        // read message
        //
        byte msg[] = new byte[4096]; int nbytes;

        fis = new FileInputStream( input);

        System.err.println( "Verifying " + input + " using " + certfile);

        while( (nbytes = fis.read( msg)) > 0)
            s.update( msg, 0, nbytes);

        fis.close();

        // check signature
        //
        File file = new File( sig);
        int len = (int) file.length();
        fis = new FileInputStream( file);
        byte b[] = new byte[len];
        nbytes = fis.read( b);
        fis.close();
        if( nbytes != len) throw new Exception( "bad read: " + sig);

        if( s.verify( b))
            System.err.println( "Signature is valid.");
        else
            System.err.println( "INVALID signature.");
    }

}
