package top.tocome.control.command;

import top.tocome.utils.Error;

import java.util.ArrayList;

/**
 * 指令集
 */
public class CommandSet extends Command {

    public CommandSet(String key) {
        super(key);
    }

    public CommandSet(String key, String describe) {
        super(key, describe);
    }

    public CommandSet(String key, String describe, String... paramsHint) {
        super(key, describe, paramsHint);
    }

    /**
     * 子指令
     */
    protected final ArrayList<Command> commands = new ArrayList<>();

    @Override
    protected Error matchAction(String cli) {
        if (cli.startsWith(Prefix)) {
            Error e;
            for (Command c : commands) {
                e = c.match(cli);
                if (e != MatchResult.Failed)
                    return e;
            }
            return MatchResult.SubFailed;
        } else {
            return super.matchAction(cli);
        }
    }

    /**
     * 添加一条子命令
     */
    public void addCommand(Command command) {
        command.setParentSet(this);
    }

    /**
     * 获取所有一级子指令的帮助
     *
     * @return key param1 param2 ... #describe1 + "\n"<br>
     * key -subKey1 param1 param2 ... #describe1 + "\n"<br>
     * key -subKey2 param1 param2 ... #describe2 + "\n"<br>
     * key -subKey3 param1 param2 ... #describe3 + "\n"<br>
     * ...
     */
    public String getAllUsage() {
        StringBuilder sb = new StringBuilder(getUsage());
        for (Command c : commands) {
            sb.append(c.getUsage());
        }
        return sb.toString();
    }
}
