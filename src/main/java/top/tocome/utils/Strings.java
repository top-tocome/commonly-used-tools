package top.tocome.utils;

/**
 * 字符串匹配
 */
public class Strings {
    /**
     * 字符串是否包含关键词其中之一
     */
    public static boolean contains(String s, String[] keys) {
        for (String key : keys) {
            if (s.contains(key)) return true;
        }
        return false;
    }

    /**
     * 将字符串转换为long
     *
     * @param defaultLong 出现错误时返回
     */
    public static long parseLong(String s, int defaultLong) {
        try {
            return Long.parseLong(s);
        } catch (Exception e) {
            return defaultLong;
        }
    }

    /**
     * 将字符串转换为int
     *
     * @param defaultInt 出现错误时返回
     */
    public static int parseInt(String s, int defaultInt) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defaultInt;
        }
    }

    /**
     * 获取文件格式
     *
     * @param filename 文件名
     * @return txt, jpg, ...
     */
    public static String getFormat(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
