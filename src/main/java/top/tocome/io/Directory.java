package top.tocome.io;

import java.io.File;
import java.util.ArrayList;

public class Directory {
    /**
     * 获取根目录下的所有目录和文件
     *
     * @param rootPath    根目录路径
     * @param files       根目录下所有文件会添加到此列表
     * @param directories 根目录下所有目录会添加到此列表
     */
    public static void getAll(String rootPath, ArrayList<File> files, ArrayList<File> directories) {
        getAll(new File(rootPath), files, directories);
    }

    /**
     * 获取根目录下的所有目录和文件
     *
     * @param rootDirectory 根目录文件
     * @param files         根目录下所有文件会添加到此列表
     * @param directories   根目录下所有目录会添加到此列表
     */
    public static void getAll(File rootDirectory, ArrayList<File> files, ArrayList<File> directories) {
        if (rootDirectory.isDirectory())
            for (File file : rootDirectory.listFiles()) {
                if (file.isDirectory()) {
                    directories.add(file);
                    getAll(file, files, directories);
                } else
                    files.add(file);
            }
    }

    /**
     * 复制源目录下所有文件到目标目录下
     *
     * @param directoryPath 源目录
     * @param savePath      目标目录
     */
    public static void copy(String directoryPath, String savePath) {
        ArrayList<File> files = new ArrayList<>();
        ArrayList<File> directories = new ArrayList<>();
        File directory = new File(directoryPath);
        getAll(directory, files, directories);

        //将字符串转换为正则表达式
        directoryPath = directory.getPath().replace("\\", "\\\\");
        savePath = savePath.replace("\\", "\\\\");

        new File(savePath).mkdirs();
        for (File d : directories) {
            new File(d.getPath().replaceFirst(directoryPath, savePath)).mkdirs();
        }
        for (File f : files) {
            top.tocome.io.File.copy(f.getPath(), f.getPath().replaceFirst(directoryPath, savePath), false);
        }
    }
}
