package com.example.demo.service.ecdsa;

import com.example.demo.service.utils.ByteArrayToBase64;
import com.example.demo.service.utils.GeneratePrivateKey;

import java.security.PrivateKey;

public class EcdsaPemSignTest {

    public static void main(String[] args) {

        try {

            String ALGORITMO = "SHA256withECDSA";
            String CHAVE_PRIVADA_PEM_P521 = "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgjhlmyGuPxizXMFrVaTwEY2oQZvMJAnhvxrYYKw1b5VKhRANCAAR/c5eFupVCoWemMVCLYjQkRHyK21CgLgqwpHc5IblHnSBlrmQm/AQxWf/ugQJNN6AcY8+j3pKv33bNLiiIjBsq";
            String MENSAGEM_ORIGINAL = "Teste:Mensagem";

            GeneratePrivateKey generatePrivateKey = new GeneratePrivateKey();
            PrivateKey PRIVATE_KEY = generatePrivateKey.generatePrivateKeyPem(CHAVE_PRIVADA_PEM_P521);

            EcdsaPemSign ecdsaPemSign = new EcdsaPemSign();
            byte[] signResult = ecdsaPemSign.sign(ALGORITMO, PRIVATE_KEY, MENSAGEM_ORIGINAL);

            String base64 = ByteArrayToBase64.byteToBase64(signResult);

            System.out.println("Assinatura Base64: " + base64);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
