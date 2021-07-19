package com.zhiyu.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author wengzhiyu
 */
public class DataHelpUtil {
    public static int getIntValue(Map data, String name) {
        return getIntValue(data.get(name));
    }

    public static String getStringValue(Map data, String name) {
        return getString(data.get(name));
    }

    public static Date getDateValue(Object val){
        if(val instanceof Date){
            return (Date)val;
        }
        return null;
    }

    public static double getDoubleValue(Object val) {
        if (isNullOrEmpty(val)) {
            return 0.0;
        }
        if (val instanceof Number) {
            return ((Number) val).doubleValue();
        }
        if (val instanceof Date) {
            return (double) ((Date) val).getTime();
        }
        try {
            return Double.parseDouble((String) val);
        } catch (Exception ex) {
            return 0.0;
        }
    }

    public static BigDecimal getBigDecimalValue(Object val) {
        if (isNullOrEmpty(val)) {
            return BigDecimal.ZERO;
        }
        if (val instanceof Number) {
            return BigDecimal.valueOf(((Number) val).doubleValue());
        }
        if (val instanceof Date) {
            return BigDecimal.valueOf(((Date) val).getTime());
        }
        try {
            return new BigDecimal((String) val);
        } catch (Exception ex) {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 获取小数点位数截掉
     * @param val
     * @param num
     * @return
     */
    public static BigDecimal format(Object val,int num){
        if (isNullOrEmpty(val)) {
            return BigDecimal.ZERO;
        }
        if (val instanceof Number) {
            return new BigDecimal(val.toString()).setScale(num, BigDecimal.ROUND_DOWN);
        }
        try {
            return new BigDecimal(val.toString()).setScale(num, BigDecimal.ROUND_DOWN);
        } catch (Exception ex) {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 获取小数点位数四舍五入
     * @param val
     * @param num
     * @return
     */
    public static BigDecimal getDecimal(Object val,int num){
        if (isNullOrEmpty(val)) {
            return BigDecimal.ZERO;
        }
        if (val instanceof Number) {
            return new BigDecimal(val.toString()).setScale(num, BigDecimal.ROUND_HALF_UP);
        }
        try {
            return new BigDecimal(val.toString()).setScale(num, BigDecimal.ROUND_HALF_UP);
        } catch (Exception ex) {
            return BigDecimal.ZERO;
        }
    }


    public static int getIntValue(Object val) {
        if (isNullOrEmpty(val)) {
            return 0;
        }
        if (val instanceof Number) {
            return ((Number) val).intValue();
        }
        if (val instanceof Date) {
            return (int) ((Date) val).getTime();
        }
        try {
            return Integer.parseInt((String) val);
        } catch (Exception ex) {
            return 0;
        }
    }



    public static long getLongValue(Object val) {
        if (isNullOrEmpty(val)) {
            return 0;
        }
        if (val instanceof Number) {
            return ((Number) val).longValue();
        }
        if (val instanceof Date) {
            return ((Date) val).getTime();
        }
        try {
            return Long.parseLong((String) val);
        } catch (Exception ex) {
            return 0L;
        }
    }



    public static boolean isNullOrEmpty(Object val) {
        if (val == null) {
            return true;
        }
        if (val instanceof Collection) {
            return ((Collection) val).isEmpty();
        }
        String s = val.toString().trim();
        return s.length() == 0;
    }

    public static String getRemoveBlankString(Object val) {
        if (isNullOrEmpty(val)) {
            return "";
        }
        return val.toString().trim();
    }

    public static String getString(Object val) {
        if (isNullOrEmpty(val)) {
            return "";
        }
        return val.toString();
    }

    public static boolean isNullOrEmpty(String val) {
        if (val == null) {
            return true;
        }
        val = val.trim();
        return val.length() == 0;
    }

    public static boolean isNullOrZero(Object val) {
        if (val == null) {
            return true;
        }
        return getLongValue(val) == 0;
    }

    public static String formatSearchString(String k, char sp) {
        if (k == null) {
            return k;
        }
        StringBuilder sb = new StringBuilder();
        boolean startSpace = false;
        boolean begin = false;
        for (int i = 0; i < k.length(); i++) {
            char ch = k.charAt(i);
            if ((ch == ' ' || ch == '\t' || ch == '\n' || ch == '\f')) {
                startSpace = true;
                continue;
            }
            if (startSpace && begin) {
                sb.append(sp);
            }
            sb.append(ch);
            begin = true;
            startSpace = false;
        }
        return sb.toString();
    }

    /**
     * 去掉字符串前后多余的空格，以及字之间多余的空格
     * @param k
     * @return
     */
    public static String trim(String k) {
        return formatSearchString(k, ' ');
    }

    public static void main(String[] args) {
        System.out.println(trim("      I     am  \t      man           "));
    }
}
