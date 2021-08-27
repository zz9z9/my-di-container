package com.example.util;

import java.util.Collection;
import java.util.Iterator;

public class StringUtils {
    public static String getCommaDelimitedString(Collection<?> coll) {
        StringBuilder sb = new StringBuilder();
        Iterator<?> iter = coll.iterator();

        while(iter.hasNext()) {
            sb.append(iter.next());
            if(iter.hasNext()) {
                sb.append(",");
            }
        }

        return sb.toString();
    }
}
