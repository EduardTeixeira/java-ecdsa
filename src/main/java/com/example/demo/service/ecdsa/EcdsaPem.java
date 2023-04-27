package com.example.demo.service.ecdsa;

import com.example.demo.service.utils.GeneratePrivateKey;
import com.example.demo.service.utils.GeneratePublicKey;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class EcdsaPem {

    public static void main(String[] args) throws Exception {

        try {

            String ALGORITMO = "SHA256withECDSA";

            //String CHAVE_PUBLICA_PEM_P521 = "MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQBpnyr74amlFTyNVRhCfWK+3BBQB+DrEwxagd91IYCBaneHLuqGEOooCYgUMaM65NcfJf52auSk5kSjRuBjbbR5ZsANQ2Bj7aEqLPSUmzlHvPnrVX+gCtqyazKnEwwGsJpsGYj/+JXvHFhzPNRnEiM0Al4H2aQGz+kPjPfRUwlf8gPwY8=";
            String CHAVE_PUBLICA_PEM_P521 = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE9SpDXJAdbrZi8E2eLSZHYCQw0kt2Re3PMJOr9HaSnycaBY6e5K7qI0wcD5+irU7rIka0VSKsrTgmA1u+UgEwaw==";

            String CHAVE_PRIVADA_PEM_P521 = "MIHuAgEAMBAGByqGSM49AgEGBSuBBAAjBIHWMIHTAgEBBEIAdBf9rlUULNly9UltRtM80Zdse4KaNAOgugBonmql2MDN+c/BUw+RPmmxb24MQMwPwEmVmjRyGfzm4FFbfQBM6uihgYkDgYYABAGmfKvvhqaUVPI1VGEJ9Yr7cEFAH4OsTDFqB33UhgIFqd4cu6oYQ6igJiBQxozrk1x8l/nZq5KTmRKNG4GNttHlmwA1DYGPtoSos9JSbOUe8+etVf6AK2rJrMqcTDAawmmwZiP/4le8cWHM81GcSIzQCXgfZpAbP6Q+M99FTCV/yA/Bjw==";

            String MENSAGEM_ORIGINAL = "Mensagem:Teste";

            // site keytool - return true
            //String SIGNATURE_BASE_64 = "MIGIAkIBMBWwLxBJ5rrglVb3piizJrlMPfdhIXo74XvQ3VbiKZDus4SRzzp2/o5IEcm73lzkq+u1Z6hnVW0j4lucd9XY9SYCQgGtsCcUC52i8UeFOtfX7rNXx9DxFfaYWvrOhLCRfhpBQ/11jOjafVPhTzP0tJjmVPlgOnvAO2Xo6H9Z2kgLB6N5vw==";

            // front - em hex
            //String SIGNATURE_BASE_64 = "00323f8274b4b6544ab082472cf23ebd149719e4c16a4ad2e6262b816a00b778948cea483162fed9bb06ba5e45781fd97ff975fa1f1a2b922b368d6a28a8069248a9015e15435a64a65261ba5d1f1fc204e28737969a7375050ad24280832974e99b73c93a920a4b7b7f7a7305ac309ef6c494fd690081c47d88480b1ea84a7705dcdc27";

            // front - hex para base64
            // String SIGNATURE_BASE_64 = "ADI/gnS0tlRKsIJHLPI+vRSXGeTBakrS5iYrgWoAt3iUjOpIMWL+2bsGul5FeB/Zf/l1+h8aK5IrNo1qKKgGkkipAV4VQ1pkplJhul0fH8IE4oc3lppzdQUK0kKAgyl06ZtzyTqSCkt7f3pzBawwnvbElP1pAIHEfYhICx6oSncF3Nwn";


            // base64
            //String SIGNATURE_BASE_64 = "AQ1AynIKc/FG48wzpMx04OnN3h9VaPI1J8qEb/nPKnbuk6EhlQqDHxb2pGK27dE6H+XzXYuyoYqEg3pI2OTDxEnIAQvt83X7lwT+8CeJeDgmEbmdvuG5UQMaOIgO3WZMdFQJDJwmYWF3OoDk4/j8vAVOuVl0jmXmU78TwTxfxXeaa0kK";

            // hex
            //String SIGNATURE_BASE_64 = "00828f04d4473a5cd80f91448a6795f7194566a80de99e12fea0abf78dbfda640161066848f4342eb120f7ffa20792a0b854473679f9889079c6f326c57f0bded2c5016f9c7ea832b972f55482009d84e9be3a4d1505cda33080ac4b6627615bd1b5153736f96373e8e905cced9a5aa696c893d2401b8920f7a6dcfc3682199ae80a95ee";
            // base64
            //String SIGNATURE_BASE_64 = "AIKPBNRHOlzYD5FEimeV9xlFZqgN6Z4S/qCr942/2mQBYQZoSPQ0LrEg9/+iB5KguFRHNnn5iJB5xvMmxX8L3tLFAW+cfqgyuXL1VIIAnYTpvjpNFQXNozCArEtmJ2Fb0bUVNzb5Y3Po6QXM7ZpappbIk9JAG4kg96bc/DaCGZroCpXu";

            String SIGNATURE_BASE_64 = "MEQCIHTA5sgvyUsbferymHCQC9has43pjHLscYXpDYIgSXT1AiAUik4gRVsrHyyQvgADNbLpHuSFYWva6rWwNERjbaPcIg==";

            /*
            String STRING_HEX = "00828f04d4473a5cd80f91448a6795f7194566a80de99e12fea0abf78dbfda640161066848f4342eb120f7ffa20792a0b854473679f9889079c6f326c57f0bded2c5016f9c7ea832b972f55482009d84e9be3a4d1505cda33080ac4b6627615bd1b5153736f96373e8e905cced9a5aa696c893d2401b8920f7a6dcfc3682199ae80a95ee";
            String resultBase64 = hexToBase64(STRING_HEX);
            System.out.println(resultBase64);
             */

            GeneratePublicKey generatePublicKey = new GeneratePublicKey();
            PublicKey PUBLIC_KEY = generatePublicKey.generatePublicKeyPem(CHAVE_PUBLICA_PEM_P521);

            GeneratePrivateKey generatePrivateKey = new GeneratePrivateKey();
            PrivateKey PRIVATE_KEY = generatePrivateKey.generatePrivateKeyPem(CHAVE_PRIVADA_PEM_P521);

            byte[] msgOriginalBytes = MENSAGEM_ORIGINAL.getBytes("UTF-8");
            //byte[] msgOriginalBytes = toByteTest(mensagemOriginal);

            byte[] signatureBytes = decodeSignature(SIGNATURE_BASE_64);
            //byte[] signatureBytes = Base64.getEncoder().encodeToString(signatureSha256.getBytes());
            //byte[] signatureBytes = toByteTest(signatureSha256);

            EcdsaPemVerify ecdsaPemVerify = new EcdsaPemVerify();
            boolean result = ecdsaPemVerify.verifySignature(ALGORITMO, PUBLIC_KEY, msgOriginalBytes, signatureBytes);

            System.out.println("Verificando a assinatura do Front ::: " + result);

            EcdsaPemSign ecdsaPemSign = new EcdsaPemSign();
            byte[] signResult = ecdsaPemSign.sign(ALGORITMO, PRIVATE_KEY, MENSAGEM_ORIGINAL);

            System.out.println(byteToString(signResult));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static byte[] decodeSignature(String signature) {

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        //byte[] signatureBytes = Base64.getEncoder().encodeToString(signatureSha256.getBytes());
        //byte[] signatureBytes = toByteTest(signatureSha256);

        return signatureBytes;
    }

    public static String hexToBase64(String hexString) {
        byte[] bytes = hexStringToByteArray(hexString); // Converter a string hexadecimal em um array de bytes
        String base64Encoded = Base64.getEncoder().encodeToString(bytes); // Codificar o array de bytes em base64
        return base64Encoded;
    }

    private static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        byte[] buffer = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            buffer[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return buffer;
    }

    /*
    public static void base64ToArrayBuffer(String base64) {

        var binaryString = atob(base64);
        var bytes = new Uint8Array(binaryString.length);
        for (var i = 0; i < binaryString.length; i++) {
            bytes[i] = binaryString.charCodeAt(i);
        }
        return bytes.buffer;
    }
     */

    public static String byteToString(byte[] bytes) {
        String r = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(r);
        return r;
    }

}
