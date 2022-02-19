package com.openpayd.foreignexchange.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatUtil
{
    public static String getCurrentIsoFormattedDate()
    {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
