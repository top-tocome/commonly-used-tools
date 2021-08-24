package top.tocome.control.command;

/**
 * 指令集构建器
 */
public class CommandSetBuilder {

    private final CommandSet commandSet;

    public CommandSetBuilder(String key, Command.OnMatched onMatched) {
        commandSet = new CommandSet(key, onMatched);
    }

    /**
     * 设置参数提示
     *
     * @see CommandSet#paramsHint
     */
    public CommandSetBuilder paramsHint(String[] paramsHint) {
        commandSet.paramsHint = paramsHint;
        return this;
    }

    /**
     * 设置指令描述
     *
     * @see CommandSet#describe
     */
    public CommandSetBuilder describe(String describe) {
        commandSet.describe = describe;
        return this;
    }

    /**
     * 添加一条副指令
     */
    public CommandSetBuilder newCommand(String key, Command.OnMatched onMatched) {
        commandSet.addCommand(new CommandBuilder(key, onMatched).build());
        return this;
    }

    /**
     * 添加一条副指令
     */
    public CommandSetBuilder newCommand(String key, String describe, Command.OnMatched onMatched) {
        commandSet.addCommand(
                new CommandBuilder(key, onMatched)
                        .describe(describe)
                        .build());
        return this;
    }

    /**
     * 添加一条副指令
     */
    public CommandSetBuilder newCommand(String key, String[] paramsHint, String describe, Command.OnMatched onMatched) {
        commandSet.addCommand(
                new CommandBuilder(key, onMatched)
                        .paramsHint(paramsHint)
                        .describe(describe)
                        .build());
        return this;
    }

    public CommandSet build() {
        return commandSet;
    }
}
