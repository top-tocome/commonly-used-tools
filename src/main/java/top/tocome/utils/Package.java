package top.tocome.utils;

import top.tocome.io.Directory;

public class Package {

    public String buildDir = "target";

    public void pack(String... projectDirs) {
        for (String dir : projectDirs) {
            Directory.copy(dir + "/" + buildDir + "/classes", "package/");
        }
    }
}
