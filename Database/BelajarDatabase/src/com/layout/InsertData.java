package com.layout;

import com.config.MyConfig;

public class InsertData {
    public static void insertData() {
        MyConfig.connection();
        MyConfig.insertData();
    }
}