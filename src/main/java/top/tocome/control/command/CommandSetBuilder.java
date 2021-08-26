package top.tocome.control.command;

/**
 * 指令集构建器
 */
public class CommandSetBuilder {

    private final CommandSet commandSet;

    public CommandSetBuilder(CommandSet commandSet) {
        this.commandSet = commandSet;
    }

    public CommandSetBuilder(String key) {
        commandSet = new CommandSet(key);
    }

    public CommandSetBuilder(String key, String describe) {
        commandSet = new CommandSet(key, describe);
    }

    public CommandSetBuilder(String key, String describe, String... paramsHint) {
        commandSet = new CommandSet(key, describe, paramsHint);
    }

    /**
     * 添加一条副指令
     */
    public CommandSetBuilder newCommand(String key, Command.OnMatchedEvent onMatchedEvent) {
        new CommandBuilder(key).onMatchedEvent(onMatchedEvent).build().setParentSet(commandSet);
        return this;
    }

    /**
     * 添加一条副指令
     */
    public CommandSetBuilder newCommand(String key, String describe, Command.OnMatchedEvent onMatchedEvent) {
        new CommandBuilder(key, describe)
                .onMatchedEvent(onMatchedEvent)
                .build().setParentSet(commandSet);
        return this;
    }

    /**
     * 添加一条副指令
     */
    public CommandSetBuilder newCommand(String key, String[] paramsHint, String describe, Command.OnMatchedEvent onMatchedEvent) {
        new CommandBuilder(key, describe, paramsHint)
                .onMatchedEvent(onMatchedEvent)
                .build().setParentSet(commandSet);
        return this;
    }

    /**
     * 设置参数提示
     *
     * @see CommandSet#paramsHint
     */
    public CommandSetBuilder paramsHint(String... paramsHint) {
        commandSet.setParamsHint(paramsHint);
        return this;
    }

    /**
     * 设置指令描述
     *
     * @see CommandSet#describe
     */
    public CommandSetBuilder describe(String describe) {
        commandSet.setDescribe(describe);
        return this;
    }

    /**
     * 设置父级指令
     *
     * @see CommandSet#parentSet
     */
    public CommandSetBuilder parentSet(CommandSet parentSet) {
        commandSet.setParentSet(parentSet);
        return this;
    }

    /**
     * 设置匹配事件
     *
     * @see CommandSet#onMatchedEvent
     */
    public CommandSetBuilder onMatchedEvent(Command.OnMatchedEvent onMatchedEvent) {
        commandSet.setOnMatchedEvent(onMatchedEvent);
        return this;
    }

    public CommandSet build() {
        return commandSet;
    }
}
