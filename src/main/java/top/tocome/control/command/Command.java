package top.tocome.control.command;

/**
 * 单条指令
 */
public class Command {
    /**
     * 副指令默认前缀
     */
    public static String defaultPrefix = "-";
    /**
     * 参数分割符号
     */
    public static String paramRegex = " ";
    /**
     * 描述开始符号
     */
    public static String descPrefix = "#";

    protected Command(String key, OnMatched onMatched) {
        this.key = key;
        this.onMatched = onMatched;
    }

    /**
     * 触发指令的关键词
     */
    public String key;
    /**
     * 指令匹配成功后执行的动作
     */
    protected OnMatched onMatched;
    /**
     * 父级指令
     */
    protected CommandSet parentSet = null;
    /**
     * 指令参数提示 默认一个参数
     * <p> 例子：  new String["参数1","参数2","参数3",....]
     *
     * @see #showParams()
     */
    protected String[] paramsHint = new String[]{""};
    /**
     * 指令描述
     */
    public String describe = "no describe";

    /**
     * 匹配指令
     *
     * @param cli 指令消息
     * @return 匹配结果
     */
    public MatchResult match(String cli) {
        cli = cli.trim();
        if (cli.startsWith(key)) {
            return matchAction(cli.replaceFirst(key, "").trim());
        }
        return MatchResult.Failed;
    }

    /**
     * 匹配成功后的行为
     *
     * @param cli 参数消息
     */
    protected MatchResult matchAction(String cli) {
        onMatched.run(parseParams(cli));
        return MatchResult.Success;
    }

    /**
     * 解析参数
     *
     * @param cli 参数消息
     * @return 参数数组
     */
    protected String[] parseParams(String cli) {
        String[] params = cli.split(paramRegex);
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].trim();
        }
        return params;
    }

    /**
     * 获取整条指令完整帮助
     *
     * @return key -subKey1 -subKey2 ... param1 param2 ... #describe + "\n"
     */
    protected String getHelp() {
        return getTotalKey() + showParams() + descPrefix + describe + "\n";
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
        return parentSet.getTotalKey() + defaultPrefix + key;
    }

    /**
     * 参数展示
     *
     * @return paramsHint[0]+{@value paramRegex}+paramsHint[1]+...+paramsHint[n]
     */
    protected String showParams() {
        StringBuilder sb = new StringBuilder();
        for (String param : paramsHint)
            sb.append(paramRegex).append(param).append("  ");
        return sb.toString().replaceFirst(paramRegex, "  ");
    }

    /**
     * 指令行为接口
     */
    public interface OnMatched {
        /**
         * @param params 指令参数数组，最大长度为{@link #paramsHint}的长度，最小长度为 1
         */
        void run(String[] params);
    }

    /**
     * 匹配结果
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
