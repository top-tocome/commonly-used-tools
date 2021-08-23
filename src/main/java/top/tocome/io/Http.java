package top.tocome.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {
    /**
     * 浏览器代理
     */
    public static String agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36";

    /**
     * 获取网络字符串
     *
     * @param url http链接
     * @return 字符串
     */
    public static String get(String url) {
        try {
            return Stream.readString(getConnection(url).getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post方法
     *
     * @param connection HttpURLConnection
     * @param message    post传递消息
     * @return post返回消息
     */
    public static byte[] post(HttpURLConnection connection, String message) {
        try {
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            Stream.write(connection.getOutputStream(), message.getBytes());
            return Stream.readBytes(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * 下载网络文件
     *
     * @param url      http链接
     * @param filepath 保存文件路径
     * @return 执行结果
     */
    public static boolean download(String url, String filepath) {
        try {
            return Stream.copy(getConnection(url).getInputStream(), new FileOutputStream(filepath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取附带默认{@link #agent User-Agent}的HttpURLConnection
     */
    public static HttpURLConnection getConnection(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("User-Agent", agent);
        return connection;
    }
}
