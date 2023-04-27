package com.example.demo.service.ecdsa;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class EcdsaPemVerify {

    public boolean verifySignature(String algoritmo, PublicKey publicKey, byte[] mensagemOriginalBytes, byte[] signatureBytes) throws Exception {

        Signature signature = Signature.getInstance(algoritmo);

        signature.initVerify(publicKey);

        signature.update(mensagemOriginalBytes);

        return signature.verify(signatureBytes);
    }

}
