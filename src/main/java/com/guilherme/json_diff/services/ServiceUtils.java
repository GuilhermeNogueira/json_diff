package com.guilherme.json_diff.services;

import java.util.Base64;

public class ServiceUtils {

    public static String decodeBase64(String encodedStr){
        return new String(Base64.getDecoder().decode(encodedStr.getBytes()));
    }
}
