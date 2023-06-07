package com.example.demo.service;

import com.example.demo.service.ecdsa.EcdsaPem;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class EcdsaService {

    private static final String ALGORITMO = "SHA256withECDSA";

    @PostMapping(value = "")
    public boolean ecdsaVerify(
            String requestBody,
            String header1,
            String header2,
            String header3,
            String header4,
            String header5,
            String header6,
            String assinatura
    ) {

        try {

            System.out.println("Header1: " + header1);
            System.out.println("Header2: " + header2);
            System.out.println("Header3: " + header3);
            System.out.println("Header4: " + header4);
            System.out.println("Header5: " + header5);
            System.out.println("Header6: " + header6);
            System.out.println("Header7: " + assinatura);

            if (
                    header1 == null ||
                            header2 == null ||
                            header3 == null ||
                            header4 == null ||
                            header5 == null ||
                            header6 == null ||
                            assinatura == null
            ) {
                return false;
            }

            String msgOriginal = header1;

            String publicKeyPem = "";

            return EcdsaPem.ecdsaVerify(ALGORITMO, publicKeyPem, header1, assinatura);

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

}
