package top.tocome.utils;

import top.tocome.io.Directory;

public class Project {
    /**
     * 项目根路径
     */
    public String projectDir;

    public Project(String projectDir) {
        this.projectDir = projectDir;
    }

    /**
     * 相对于{@link #projectDir}的类路径
     */
    public String classDir = "target/classes";
    /**
     * 相对于{@link #projectDir}的源路径
     */
    public String srcDir = "src/main/java";
    /**
     * 打包至该目录
     */
    public String packDir = "package";

    /**
     * 打包编译后的类文件
     */
    public void packClass() {
        Directory.copy(projectDir + "/" + classDir, packDir + "/class");
    }

    /**
     * 打包源代码
     */
    public void packSrc() {
        Directory.copy(projectDir + "/" + srcDir, packDir + "/src");
    }
}
