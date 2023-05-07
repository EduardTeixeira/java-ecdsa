package com.example.demo.service.crypto;

import com.example.demo.service.utils.RsaUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.util.Base64;

public class CriptDescriptEAS {

    private static Cipher cipher;

    private static void setaChipher(int comando) throws Exception {
        String initVector = "ZW5jcnlwdGlvbklu";
        String key = "H4DyVzcJqATH8tJwCFzv8hhO896ThyKL";
        String protocol = "AES/CBC/PKCS5PADDING"; // PKCS5PADDING
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        cipher = Cipher.getInstance(protocol);
        cipher.init(comando, skeySpec, iv);
    }

    public static String encrypt(String valor) {
        try {
            setaChipher(Cipher.ENCRYPT_MODE);
            return Base64.getEncoder().encodeToString(cipher.doFinal(valor.getBytes()));
        } catch (Exception e) {
            return valor;
        }
    }

    public static String decrypt(String valor) {
        try {
            setaChipher(Cipher.DECRYPT_MODE);
            return new String(cipher.doFinal(Base64.getDecoder().decode(valor)));
        } catch (Exception e) {
            return valor;
        }
    }

    private static String privateKey = "";
    private static String publicKey = "";

    public static void main(String[] args)
            throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {

        String certOriginal = "";

        String certCrypto = "";

        try {

            String[] certCryptoSplit = certCrypto.split("@#");

            StringBuffer stringBuffer = new StringBuffer();

            for (String certyCryptoPartes : certCryptoSplit) {
                System.out.println("PARTE :::::::::::::: " + certyCryptoPartes);
                //String decryptCert = decrypt(certyCryptoPartes);
                String decryptCert = RsaUtils.decrypt(certyCryptoPartes, privateKey);
                System.out.println("decryptCert :::::::: " + decryptCert);
                /*
                stringBuffer.append(
                        //RsaUtils.decrypt(certEncrypt, privateKey)
                        decrypt(certEncrypt)
                );
                 */
            }

            String certDecrypt = stringBuffer.toString();
            System.out.println("Cert Decrypt ::: " + certDecrypt);

            //String valueDecrypt = listParametroCriptado.stream().map(item -> decrypt(item)).collect(Collectors.joining());


            String result = encrypt("");
            System.out.println("ENCRYPT ::: " + result);

            result = decrypt(result);
            System.out.println("DECRYPT ::: " + result);

            // String cryptoTest = "";
            String cryptoTest = "";
            String resultCrypto = decrypt(cryptoTest);
            System.out.println("\nDECRYPT (cryptoTest) ::: " + resultCrypto);

            byte[] bytes = resultCrypto.getBytes("UTF-8");
            String s2 = new String(bytes, "UTF-8");
            System.out.println("Resultado (resultCrypto) ::: " + s2);


            System.out.println("\n Resultados da RSA UTILS ... ");

            String encryptedString = Base64.getEncoder().encodeToString(RsaUtils.encrypt("EduÁrd", publicKey));
            System.out.println("encryptedString ::: " + encryptedString);

            String decryptedString = RsaUtils.decrypt(
                    encryptedString,
                    privateKey
            );

            System.out.println("decryptedString ::: " + decryptedString);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
