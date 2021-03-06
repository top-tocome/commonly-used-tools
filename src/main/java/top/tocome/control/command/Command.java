package top.tocome.control.command;

import top.tocome.utils.Error;

/**
 * 单条指令
 */
public class Command {
    /**
     * 副指令默认前缀
     */
    public static String Prefix = "-";
    /**
     * 参数分割符号
     */
    public static String ParamRegex = " ";
    /**
     * 描述开始符号
     */
    public static String DescPrefix = "#";

    public Command(String key) {
        setKey(key);
    }

    public Command(String key, String describe) {
        this(key);
        setDescribe(describe);
    }

    public Command(String key, String describe, String... paramsHint) {
        this(key, describe);
        setParamsHint(paramsHint);
    }

    /**
     * 触发指令的关键词
     */
    protected String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 指令参数提示 默认一个参数
     * <p> 例子：  new String["参数1","参数2","参数3",....]
     *
     * @see #showParams()
     */
    protected String[] paramsHint = new String[]{"<no params>"};

    public void setParamsHint(String[] paramsHint) {
        if (paramsHint != null && paramsHint.length > 0) this.paramsHint = paramsHint;
    }

    /**
     * 指令描述
     */
    protected String describe = "no describe";

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 指令匹配成功后执行的动作
     */
    protected OnMatchedEvent onMatchedEvent = null;

    public void setOnMatchedEvent(OnMatchedEvent onMatchedEvent) {
        this.onMatchedEvent = onMatchedEvent;
    }

    /**
     * 父级指令
     */
    protected CommandSet parentSet = null;

    /**
     * 添加父级指令
     * 等同与{@link CommandSet#addCommand(Command this)}
     *
     * @param parentSet 父指令集
     */
    public void setParentSet(CommandSet parentSet) {
        if (this.parentSet != null) this.parentSet.commands.remove(this);
        this.parentSet = parentSet;
        parentSet.commands.add(this);
    }

    /**
     * 从最高级父指令开始匹配
     */
    public Error matchFromTopParent(String cli) {
        if (parentSet != null) return parentSet.matchFromTopParent(cli);
        return match(cli);
    }

    /**
     * 从本级指令开始匹配
     *
     * @param cli 指令消息
     * @return 匹配结果
     */
    public Error match(String cli) {
        //pattern sample : "^ *(?:|-)key(?:[- ].*|)"
        String commandPreRegex = "^ *(?:|" + Prefix + ")" + key;
        if (cli.matches(commandPreRegex + "(?:[" + Prefix + " ].*|)")) {
            return matchAction(cli.replaceFirst(commandPreRegex + " *", ""));
        }
        return MatchResult.Failed;
    }

    /**
     * 匹配成功后的行为
     *
     * @param cli 参数消息
     */
    protected Error matchAction(String cli) {
        if (onMatchedEvent != null)
            return onMatchedEvent.run(parseParams(cli));
        return MatchResult.NoEvent;
    }

    /**
     * 解析参数
     *
     * @param cli 参数消息
     * @return 参数数组
     */
    protected String[] parseParams(String cli) {
        return cli.split(" *" + ParamRegex + " *");
    }

    /**
     * 获取整条指令完整帮助
     *
     * @return key -subKey1 -subKey2 ... param1 param2 ... #describe + "\n"
     */
    public String getUsage() {
        return getTotalKey() + showParams() + DescPrefix + describe + "\n";
    }

    /**
     * 获取完整的指令关键词
     *
     * @return parentKey+key
     */
    protected String getTotalKey() {
        if (parentSet == null) {
            return key;
        }
        return parentSet.getTotalKey() + Prefix + key;
    }

    /**
     * 参数展示
     *
     * @return paramsHint[0]+{@value ParamRegex}+paramsHint[1]+...+paramsHint[n]
     */
    protected String showParams() {
        StringBuilder sb = new StringBuilder();
        for (String param : paramsHint)
            sb.append(ParamRegex).append(param).append("  ");
        return sb.toString().replaceFirst(ParamRegex, "  ");
    }

    /**
     * 指令行为接口
     */
    public interface OnMatchedEvent {
        /**
         * @param params 指令参数数组，最大长度为{@link #paramsHint}的长度，最小长度为 1
         */
        Error run(String[] params);
    }

    /**
     * 指令匹配结果
     */
    public static class MatchResult {
        /**
         * 指令匹配且执行成功
         */
        public static Error Success = new Error("执行成功");

        /**
         * 主指令匹配成功，但副指令匹配失败
         */
        public static Error SubFailed = new Error("没有该指令");

        /**
         * 匹配成功，但未设置事件，运行失败
         */
        public static Error NoEvent = new Error("该指令未绑定事件");

        /**
         * 执行时参数出错
         */
        public static Error ParamError = new Error("参数错误");

        /**
         * 主指令匹配失败
         */
        public static Error Failed = new Error("指令错误");
    }
}
