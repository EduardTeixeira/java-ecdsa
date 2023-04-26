package com.example.demo.service.ecdsa;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EcdsaVerifyPem {

    public static void main(String[] args) throws Exception {

        try {

            // Para realizar a verificação da ASSINATURA - ECDSA é necessário dois dados
            // MENSAGEM_ORIGINAL (nesse caso o Header Original) e MENSAGEM_ORIGINAL_SHA256_WITH_ECDSA
            // Com esses dados é possível fazer a verificação da assinatura
            //
            // Lembrando que não é possível resgatar os valores de assinatura ECDSA, porque o objetivo é apenas autenticar
            // e não trafegar dados para serem descriptografados, sendo apenas de verificação
            //
            // Nesse caso poderiamos adotar de enviar os dados como é feito no JWT...
            // DADO1.DADO2.DADO3
            // no nosso caso
            // MENSAGEM_ORIGINAL.MENSAGEM_ORIGINAL_SHA256_WITH_ECDSA.TALVEZ_OS_DADOS_CRIPTOGRAFIA_RSA_256

            String ALGORITMO = "SHA256withECDSA";
            String CHAVE_PUBLICA_P521 = "MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQBaV7pejxgjDACkPDnphsgOjc76L3mgJr52+dTUiNlvxWLfXGzx/QmsppjuYFL0PTIU/pEl4N2bOoNBdxmmsbgldcAY5yf52fmEg4L2OHErD4FOaMys0rmJjdxMYjJyCnQorxc8QshVGI80ij2c/k/gNjyDhvMSUC41rtvW1zp5GDlJp8=";
            //String CHAVE_PRIVADA_P521 = "MIHuAgEAMBAGByqGSM49AgEGBSuBBAAjBIHWMIHTAgEBBEIB+QbxQKrxi3wCS1mKDwIHnHrw1y+SMcxr691/NKaPJj1YalZD+VgjhrUkS1h4lsknqOprDUfsz2a47U8mXMul9mKhgYkDgYYABAFpXul6PGCMMAKQ8OemGyA6NzvoveaAmvnb51NSI2W/FYt9cbPH9CaymmO5gUvQ9MhT+kSXg3Zs6g0F3GaaxuCV1wBjnJ/nZ+YSDgvY4cSsPgU5ozKzSuYmN3ExiMnIKdCivFzxCyFUYjzSKPZz+T+A2PIOG8xJQLjWu29bXOnkYOUmnw==";
            String HEADER_ACCESS_SIGNATURE = "Apenas uma mensagem de teste!...@";
            String SIGNATURE_BASE_64 = "MIGHAkEghDFs725ItfdFqNGerJdFSZo1pT6KurPOiYXtBZ5t/LUPRP8flo0Ga3U9lg05Ug1Zzdkf3v8X41KqnVrC3iEVCgJCAdTzkAAOSy6ybftzoO6u+S+1Pqc95YiUIG4bGPQ/dh598C05UQMtXjUYngGolGfwd+b50dmRGjIXRwC4wy9+avEC";

            // Create signature object
            Signature signature = Signature.getInstance(ALGORITMO);

            // Get public key
            byte[] publicKeyBytes = Base64.getDecoder().decode(CHAVE_PUBLICA_P521);
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PublicKey PUBLIC_KEY = keyFactory.generatePublic(publicKeySpec);

            // Verify signature
            byte[] HEADER_ACCESS_SIGNATURE_BYTES = HEADER_ACCESS_SIGNATURE.getBytes("UTF-8");
            byte[] SIGNATURE_BASE_64_BYTES = Base64.getDecoder().decode(SIGNATURE_BASE_64);
            signature.initVerify(PUBLIC_KEY);
            signature.update(HEADER_ACCESS_SIGNATURE_BYTES);
            boolean result = signature.verify(SIGNATURE_BASE_64_BYTES);

            System.out.println("Signature verification result: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
