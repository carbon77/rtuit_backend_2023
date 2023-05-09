package com.rtuit.backend;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Utility {

    public static String timestampToFormatString(Timestamp timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timestamp);
    }
}
