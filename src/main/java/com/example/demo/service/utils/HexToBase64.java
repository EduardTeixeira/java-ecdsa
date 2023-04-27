package com.example.demo.service.utils;

import java.util.Base64;

public class HexToBase64 {

    public static void main(String[] args) {
        String hexString = "AU1gwpczHMKTw6YqH8KEwpHDr8KmdMOSGU0UfcK+wohUw5TCrkbDkUQcHcO+w4QsQcK3GcKnScOJFhoReMKIYsK9wr0nJysSGcK0MwzDvMO8wqDDs8KIK8Kvw5zDjRspAF7CvMKkw5M+wpdswo0oeMK5w5fCksOcKBjDmsOmaMOLwr/Dln3ChsOOw64tK8KBFsOpwoNAXXV0WBXDgcOucHQceMOKUmYMciISWEvCvFRTQ8KXw6HDmsONwr0PSGU="; // string hexadecimal
        byte[] bytes = hexStringToByteArray(hexString); // converter para um array de bytes
        String base64String = Base64.getEncoder().encodeToString(bytes); // converter para base64
        System.out.println(base64String); // imprimir a string em base64
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

}
