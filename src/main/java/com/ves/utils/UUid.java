package com.ves.utils;

import java.util.UUID;

public class UUid {

    public static String getUUid(){
        return UUID.randomUUID().toString();
    }
}
