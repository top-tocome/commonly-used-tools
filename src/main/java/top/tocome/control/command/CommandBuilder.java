package top.tocome.control.command;

/**
 * 指令构建器
 */
public class CommandBuilder {

    private final Command command;

    public CommandBuilder(Command command) {
        this.command = command;
    }

    public CommandBuilder(String key) {
        command = new Command(key);
    }

    public CommandBuilder(String key, String describe) {
        command = new Command(key, describe);
    }

    public CommandBuilder(String key, String describe, String... paramsHint) {
        command = new Command(key, describe, paramsHint);
    }

    /**
     * 设置参数提示
     *
     * @see Command#paramsHint
     */
    public CommandBuilder paramsHint(String... paramsHint) {
        command.setParamsHint(paramsHint);
        return this;
    }

    /**
     * 设置指令描述
     *
     * @see Command#describe
     */
    public CommandBuilder describe(String describe) {
        command.setDescribe(describe);
        return this;
    }

    /**
     * 设置父级指令
     *
     * @see Command#parentSet
     */
    public CommandBuilder parentSet(CommandSet parentSet) {
        command.setParentSet(parentSet);
        return this;
    }

    /**
     * 设置匹配事件
     *
     * @see Command#onMatchedEvent
     */
    public CommandBuilder onMatchedEvent(Command.OnMatchedEvent onMatchedEvent) {
        command.setOnMatchedEvent(onMatchedEvent);
        return this;
    }

    public Command build() {
        return command;
    }
}