package com.example.demo.service.ecdsa;

import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.security.Signature;

@Component
public class EcdsaPemVerify {

    public static boolean verifySignature(String algoritmo, PublicKey publicKey, byte[] msgOriginalBytes, byte[] signatureBytes) throws Exception {

        Signature signature = Signature.getInstance(algoritmo);

        signature.initVerify(publicKey);

        signature.update(msgOriginalBytes);

        return signature.verify(signatureBytes);
    }

}
