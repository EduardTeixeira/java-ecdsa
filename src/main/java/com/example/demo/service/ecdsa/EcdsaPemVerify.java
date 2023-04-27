package com.example.demo.service.ecdsa;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class EcdsaPemVerify {

    public boolean verifySignature(String algoritmo, PublicKey PUBLIC_KEY, byte[] mensagemOriginalBytes, byte[] signatureBytes) throws Exception {

        Signature signature = Signature.getInstance(algoritmo);

        signature.initVerify(PUBLIC_KEY);

        signature.update(mensagemOriginalBytes);

        return signature.verify(signatureBytes);
    }

    private byte[] toByteTest(String inputString) {

        Charset charset = StandardCharsets.US_ASCII;

        byte[] byteArrray = charset.encode(inputString).array();

        System.out.println(byteArrray);

        return byteArrray;
    }

    /*
    public boolean verifyBytes(PublicKey publicKey, byte[] signed, String message, String algoritmo) throws Exception {

        Signature signature = Signature.getInstance(algoritmo);

        signature.initVerify(publicKey);

        signature.update(message.getBytes());

        return signature.verify(signed);
    }

    public boolean verifyUtils(String publicKeyPath, String tokenJwt, String mensagemOriginal) throws Exception {

        String headerJWT = tokenJwt.split("\\.")[0];
        String payloadJWT = tokenJwt.split("\\.")[1];
        String signatureJWT = tokenJwt.split("\\.")[2];
        String json = headerJWT + "." + payloadJWT;

        byte[] byteArray = json.getBytes("UTF-8");

        byte[] signatureBytes = Base64.decodeBase64(signatureJWT);

        Signature signature = Signature.getInstance("SHA256withRSA");

        signature.initVerify(getPublicKey(publicKeyPath));

        signature.update(byteArray);

        boolean verified = signature.verify(signatureBytes);

        return verified;
    }

     */

}
