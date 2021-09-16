package top.tocome.data;

/**
 * 储存数据格式的文件后缀名
 */
public class DataFormat {
    /**
     * Json数据
     */
    public static final DataFormat JSON = new DataFormat(".json");
    /**
     * Yaml数据
     */
    public static final DataFormat YAML = new DataFormat(".yml");

    /**
     * 文件后缀名
     */
    protected String suffixName;

    public DataFormat(String suffixName) {
        this.suffixName = suffixName;
    }

}
