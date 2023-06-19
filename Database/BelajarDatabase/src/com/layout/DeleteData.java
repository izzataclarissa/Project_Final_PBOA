package com.layout;

import com.config.MyConfig;

public class DeleteData {
    public static void deleteData() {
        MyConfig.connection();
        MyConfig.deleteData();
    }
}
