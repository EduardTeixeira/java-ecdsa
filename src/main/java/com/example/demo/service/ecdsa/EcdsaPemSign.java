package com.example.demo.service.ecdsa;

import java.security.PrivateKey;
import java.security.Signature;

public class EcdsaPemSign {

    public byte[] sign(String algoritmo, PrivateKey privateKey, String message) throws Exception {

        Signature signature = Signature.getInstance(algoritmo);

        signature.initSign(privateKey);

        signature.update(message.getBytes());

        return signature.sign();
    }

}


