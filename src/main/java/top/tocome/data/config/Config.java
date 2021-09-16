package top.tocome.data.config;

import top.tocome.io.File;

/**
 * 配置文件
 */
public abstract class Config {
    /**
     * 数据加载后赋值给当前类中名为"Instance"的静态实例
     */
    protected static final String StaticInstanceName = "Instance";

    public void load(String filepath) {
        try {
            this.getClass().getField(StaticInstanceName).set(this, parseConfigObject(filepath));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void save(String savePath) {
        File.write(savePath, serializeToString().getBytes());
    }

    /**
     * 序列化为字符串
     */
    protected abstract String serializeToString();

    /**
     * 解析为当前类的实例
     */
    protected abstract Config parseConfigObject(String filepath);
}
