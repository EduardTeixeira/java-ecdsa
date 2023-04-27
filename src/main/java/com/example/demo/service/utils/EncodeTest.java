package com.example.demo.service.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EncodeTest {

    public static void main(String[] args) {

        String inputString = "AIhhllqnj/C/AElGPCSwtnUjdgHhu10G7N29Cp5K99kjJ2/A/UZ84Wq3dkZoZpkfpk8dt6HwgLrYIRZHu8Xts9/3AE6TOv7vNagwqP2nAj+DwAFW7uuk6fKsXyLFeSdxDPlYyR+OM2STi2j3Bc/xUBaWr1nEvA8ycRefUU6MzxcwD3AF";

        Charset charset = StandardCharsets.US_ASCII;

        byte[] byteArrray = charset.encode(inputString).array();

        System.out.println(byteArrray);

        /*
        assertArrayEquals(
                new byte[] { 72, 101, 108, 108, 111, 32, 63, 63, 63, 63, 63, 33 },
                byteArrray);

         */

    }

}
