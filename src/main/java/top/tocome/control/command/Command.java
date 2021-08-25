package top.tocome.control.command;

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
        this.key = key;
    }

    public Command(String key, String describe) {
        this.key = key;
        this.describe = describe;
    }

    public Command(String key, String describe, String... paramsHint) {
        this.key = key;
        this.describe = describe;
        if (paramsHint.length > 0) this.paramsHint = paramsHint;
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
    protected String[] paramsHint = new String[]{"<no paramHint>"};

    public void setParamsHint(String[] paramsHint) {
        this.paramsHint = paramsHint;
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

    public void setParentSet(CommandSet parentSet) {
        this.parentSet = parentSet;
    }

    /**
     * 从最高级父指令开始匹配
     */
    public MatchResult matchFromTopParent(String cli) {
        if (parentSet != null) return parentSet.matchFromTopParent(cli);
        return match(cli);
    }

    /**
     * 从本级指令开始匹配
     *
     * @param cli 指令消息
     * @return 匹配结果
     */
    public MatchResult match(String cli) {
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
    protected MatchResult matchAction(String cli) {
        if (onMatchedEvent != null) onMatchedEvent.run(parseParams(cli));
        return MatchResult.Success;
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
    protected String getHelp() {
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
        void run(String[] params);
    }

    /**
     * 指令匹配结果
     */
    public enum MatchResult {
        /**
         * 指令匹配且执行成功
         */
        Success,
        /**
         * 主指令匹配成功，但副指令匹配失败
         */
        SubFailed,
        /**
         * 主指令匹配失败
         */
        Failed
    }
}
