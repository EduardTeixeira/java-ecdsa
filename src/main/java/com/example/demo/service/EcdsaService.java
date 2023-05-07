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
            String header7
    ) {

        try {

            System.out.println("Header1: " + header1);
            System.out.println("Header2: " + header2);
            System.out.println("Header3: " + header3);
            System.out.println("Header4: " + header4);
            System.out.println("Header5: " + header5);
            System.out.println("Header6: " + header6);
            System.out.println("Header7: " + header7);

            if (
                    header1 == null ||
                            header2 == null ||
                            header3 == null ||
                            header4 == null ||
                            header5 == null ||
                            header6 == null ||
                            header7 == null
            ) {
                return false;
            }

            // remover os espaços em branco da String
            requestBody = requestBody.replaceAll("\\s", "");

            String msgOriginal = header1 +  header2 + header3 +  header4 +  header5  + header6 + header7;

            String publicKeyPem = "MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQB9q4hd3GOFk/lyA7qxTBNLBCwKOys/Uer8N/Czjt0neHOwzQKqbhDZDv8n9UC1bRVPSNdpS3iANtMQrydTJTum+gBTNaGazjP2oOBFgKi/CVT07eUt8CyuSl/cp4Y59p6saSGPPjf91fVAeIWjnnwAdUDzzsxZaoT2/Rw/YgaytQQmHc=";

            return EcdsaPem.ecdsaVerify(ALGORITMO, publicKeyPem, msgOriginal, header1);

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

}
