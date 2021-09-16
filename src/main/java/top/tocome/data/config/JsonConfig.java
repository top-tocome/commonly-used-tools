package top.tocome.data.config;

import com.alibaba.fastjson.JSON;
import top.tocome.io.File;

/**
 * 配置文件序列化为Json格式
 */
public abstract class JsonConfig extends Config {

    @Override
    protected String serializeToString() {
        return JSON.toJSONString(this);
    }

    @Override
    protected Config parseConfigObject(String filepath) {
        return JSON.parseObject(File.read(filepath), this.getClass());
    }
}
