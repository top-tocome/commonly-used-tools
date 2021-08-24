package top.tocome.control.command;

/**
 * 指令构建器
 */
public class CommandBuilder {

    private final Command command;

    public CommandBuilder(String key, Command.OnMatched onMatched) {
        command = new Command(key, onMatched);
    }

    /**
     * 设置参数提示
     *
     * @see Command#paramsHint
     */
    public CommandBuilder paramsHint(String[] paramsHint) {
        command.paramsHint = paramsHint;
        return this;
    }

    /**
     * 设置指令描述
     *
     * @see Command#describe
     */
    public CommandBuilder describe(String describe) {
        command.describe = describe;
        return this;
    }

    public Command build() {
        return command;
    }
}