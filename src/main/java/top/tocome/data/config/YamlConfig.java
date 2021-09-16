package top.tocome.data.config;

import org.yaml.snakeyaml.Yaml;
import top.tocome.io.File;

/**
 * 配置文件序列化为Yaml格式
 */
public abstract class YamlConfig extends Config {

    protected Yaml yaml = new Yaml();

    @Override
    protected String serializeToString() {
        return yaml.dumpAsMap(this);
    }

    @Override
    protected Config parseConfigObject(String filepath) {
        return yaml.loadAs(File.read(filepath), this.getClass());
    }
}
