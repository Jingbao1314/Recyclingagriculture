package org.discuz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * Created by xiao on 17-11-21.
 */
public class Timetest {

    public static String TimeStamp2Date(String timestampString) {
        System.out.println("this is time :"+timestampString);
        String date = "";
        if (timestampString != null) {
            String formats = "yyyy-MM-dd HH:mm:ss";
            Long timestamp = Long.parseLong(timestampString) * 1000;
            date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        }

        return date;
    }
}