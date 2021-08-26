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
        new CommandBuilder(key).onMatchedEvent(onMatchedEvent).parentSet(commandSet);
        return this;
    }

    /**
     * 添加一条副指令
     */
    public CommandSetBuilder newCommand(String key, String describe, Command.OnMatchedEvent onMatchedEvent) {
        new CommandBuilder(key, describe)
                .onMatchedEvent(onMatchedEvent)
                .parentSet(commandSet);
        return this;
    }

    /**
     * 添加一条副指令
     */
    public CommandSetBuilder newCommand(String key, String[] paramsHint, String describe, Command.OnMatchedEvent onMatchedEvent) {
        new CommandBuilder(key, describe, paramsHint)
                .onMatchedEvent(onMatchedEvent)
                .parentSet(commandSet);
        return this;
    }

    public CommandSet build() {
        return commandSet;
    }
}
