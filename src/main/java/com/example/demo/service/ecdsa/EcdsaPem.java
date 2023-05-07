package com.example.demo.service.ecdsa;

import com.example.demo.service.utils.GeneratePublicKeyEcdsa;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class EcdsaPem {

    public static boolean ecdsaVerify(String algoritmo, String publicKeyPem, String msgOriginal, String accessSignature) throws Exception {

        try {

            return EcdsaPemVerify.verifySignature(
                    algoritmo,
                    GeneratePublicKeyEcdsa.generatePublicKeyPem(publicKeyPem),
                    msgOriginal.getBytes("UTF-8"),
                    Base64.getDecoder().decode(accessSignature)
            );

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

        return false;
    }

}
