package top.tocome.io;

import java.io.*;

/**
 * 基础流控制
 * 仅封装一次性读写
 * 不支持分段读写
 */
public class Stream {
    /**
     * 字节缓存大小8K
     */
    public static int byteBufferSize = 8192;

    /**
     * 读取该输入流所有字节
     *
     * @param inputStream 输入流
     * @return 字节数组, 超过大小时会new byte[0]
     */
    public static byte[] readBytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (copy(inputStream, byteArrayOutputStream))
            return byteArrayOutputStream.toByteArray();
        return new byte[0];
    }

    /**
     * 读取该输入流所有字符
     *
     * @param inputStream 输入流
     * @return 字符串, 超出大小返回 new String(new byte[0])
     */
    public static String readString(InputStream inputStream) {
        return new String(readBytes(inputStream));
    }

    /**
     * 标准控制台行读取
     *
     * @return 控制台输入的一行字符
     */
    public static String readLine() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            return bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将数据写出到输出流
     *
     * @param outputStream 输出流
     * @param contents     数据
     * @return 执行结果：成功or失败
     */
    public static boolean write(OutputStream outputStream, byte[] contents) {
        try {
            outputStream.write(contents);
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 基础流方法
     * 复制输入流数据到输出流
     *
     * @param inputStream  输入流
     * @param outputStream 输出流
     * @return 执行结果：成功or失败
     */
    public static boolean copy(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] b = new byte[byteBufferSize];
            int t;
            while (true) {
                t = inputStream.read(b);
                if (t == -1) {
                    break;
                } else {
                    outputStream.write(b, 0, t);
                }
            }
            inputStream.close();
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
