package com.example.demo.service.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ByteArrayToBase64 {

    public static String byteToBase64(byte[] textBytes){

        byte[] bytesBase64 = Base64.getEncoder().encode(textBytes);

        String base64 = new String(bytesBase64, StandardCharsets.UTF_8);

        return base64;
    }

}
