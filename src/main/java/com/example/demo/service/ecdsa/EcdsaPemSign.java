package com.example.demo.service.ecdsa;

import java.security.PrivateKey;
import java.security.Signature;

public class EcdsaPemSign {

    public byte[] sign(String algoritmo, PrivateKey PRIVATE_KEY, String message) throws Exception {

        Signature signature = Signature.getInstance(algoritmo);

        signature.initSign(PRIVATE_KEY);

        signature.update(message.getBytes());

        return signature.sign();
    }

}


