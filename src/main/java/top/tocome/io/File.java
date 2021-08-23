package top.tocome.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class File {
    /**
     * 读取文件
     *
     * @param filePath 源文件路径
     * @return 文件内容
     */
    public static String read(String filePath) {
        try {
            return Stream.readString(new FileInputStream(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写入文件
     *
     * @param filePath 源文件路径
     * @param contents 写入的内容
     * @param append   添加或覆盖
     * @return 执行结果
     */

    public static boolean write(String filePath, byte[] contents, boolean append) {
        try {
            return Stream.write(new FileOutputStream(filePath, append), contents);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 复制文件
     *
     * @param filePath 源文件路径
     * @param savePath 保存的路径
     * @param append   添加或覆盖
     * @return 执行结果
     */
    public static boolean copy(String filePath, String savePath, boolean append) {
        try {
            return Stream.copy(new FileInputStream(filePath), new FileOutputStream(savePath, append));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
