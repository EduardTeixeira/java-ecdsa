package com.example.demo.service.ecdsa;

import com.example.demo.service.utils.GeneratePublicKey;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EcdsaPem {

    public static void main(String[] args) throws Exception {

        try {

            String ALGORITMO = "SHA256withECDSA";

            String CHAVE_PUBLICA_PEM_P521 = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEf3OXhbqVQqFnpjFQi2I0JER8ittQoC4KsKR3OSG5R50gZa5kJvwEMVn/7oECTTegHGPPo96Sr992zS4oiIwbKg==";

            String MENSAGEM_ORIGINAL = "Teste:Mensagem";

            String SIGNATURE_BASE_64 = "MEQCIA59ewGqbZhvyPOEjEB+O43Wx5Lkhw8MJBgMrkzpUeQMAiAvHh6SvIQI9U+xmg45PH2wbTPPnVM+loeYsjlDvz4iDw==";

            GeneratePublicKey generatePublicKey = new GeneratePublicKey();
            PublicKey PUBLIC_KEY = generatePublicKey.generatePublicKeyPem(CHAVE_PUBLICA_PEM_P521);

            byte[] msgOriginalBytes = MENSAGEM_ORIGINAL.getBytes("UTF-8");

            byte[] signatureBytes = decodeSignature(SIGNATURE_BASE_64);

            EcdsaPemVerify ecdsaPemVerify = new EcdsaPemVerify();
            boolean result = ecdsaPemVerify.verifySignature(ALGORITMO, PUBLIC_KEY, msgOriginalBytes, signatureBytes);

            System.out.println("Verificando a assinatura do Front ::: " + result);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EcdsaPem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(EcdsaPem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(EcdsaPem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EcdsaPem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(EcdsaPem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(EcdsaPem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static byte[] decodeSignature(String signature) {
        return Base64.getDecoder().decode(signature);
    }

}
