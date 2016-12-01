package com.morse.mutils;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    /**
     * 获取文件名
     *
     * @param string
     * @return
     */
    public static String getVoideName(String string) {
        if (string.contains("\'")) {
            return string.substring(string.lastIndexOf("\'"));
        }
        return string.substring(string.lastIndexOf("/") + 1);
    }

    /**
     * 根据path获取下载路径
     *
     * @param string
     * @return
     */
    public static String getSubPath(String string) {
        String path = getVoideName(string);
        if (path.contains("]")) {
            return path.substring(path.indexOf("]") + 1);
        }
        return path;
    }

    /**
     * 去除字符串中起始位置的"["和末尾的"]"
     *
     * @param string
     * @return String
     */
    public static String getSubUri(String string) {
        if (!TextUtils.isEmpty(string) && string.startsWith("[") && string.endsWith("]")) {
            String str = string.substring(1, string.length() - 1);
            if (str.contains("[")) {
                String str1 = str.substring(0, str.indexOf("["));
                if (str1.contains("+")) {
                    return str1.replaceAll("\\+", " ");
                }
                return str1;
            } else {
                if (str.contains("+")) {
                    return str.replaceAll("\\+", " ");
                }
                return str;
            }
        } else if (string.contains("+")) {
            return string.replaceAll("\\+", " ");
        }
        return string;
    }

    /**
     * 判断字符串是否包含前缀
     *
     * @param string
     * @param prefix
     * @return
     */
    public static boolean isContainPrefix(String string, String prefix) {
        if (!TextUtils.isEmpty(string)) {
            return string.startsWith(prefix);
        }
        return false;
    }

    /**
     * 获取字符串的字节数
     *
     * @param string
     * @return
     */
    public static int getByteLength(String string) {
        if (TextUtils.isEmpty(string)) {
            return 0;
        }
        return string.getBytes().length;
    }

    /**
     * 获取字符串的字节数
     *
     * @param string
     * @return
     */
    public static int getCharCount(String string, String replaceStr) {
        if (TextUtils.isEmpty(string))
            return 0;
        if (TextUtils.isEmpty(replaceStr))
            return 0;
        int length = string.length();
        String str = string.replaceAll(replaceStr, "");
        return length - str.length();
    }

    /**
     * 获取字符串最大字节数
     *
     * @param max
     * @param string
     * @return
     */
    public static String getMaxByte(int max, String string) {
        if (TextUtils.isEmpty(string))
            return "";

        if (string.getBytes().length > max) {
            StringBuffer buffer = new StringBuffer(string);
            for (int i = 0; i < string.length(); i++) {
                int length = buffer.substring(0, i + 1).getBytes().length;
                if (length > max) {
                    return buffer.substring(0, i);
                } else if (length == max) {
                    return buffer.substring(0, i + 1);
                }
            }
        }
        return string;
    }

    /**
     * 删除字符串中的回车换行
     *
     * @param str
     */
    public static String dele(String str) {

        if (!TextUtils.isEmpty(str)) {
            Pattern p = Pattern.compile("\\s*");
            Matcher m = p.matcher(str);
            return m.replaceAll("");
        } else {
            return null;
        }
    }

    public static void setSelection(EditText editText) {
        int length = editText.getText().toString().length();
        editText.setSelection(length);
    }
}
