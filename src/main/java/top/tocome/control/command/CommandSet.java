package top.tocome.control.command;

import java.util.ArrayList;

/**
 * 指令集
 */
public class CommandSet extends Command {

    protected CommandSet(String key, OnMatched onMatched) {
        super(key, onMatched);
    }

    /**
     * 子指令
     */
    protected final ArrayList<Command> commands = new ArrayList<>();

    @Override
    protected MatchResult matchAction(String cli) {
        if (cli.startsWith(defaultPrefix)) {
            cli = cli.replaceFirst(defaultPrefix, "").trim();
            MatchResult result;
            for (Command c : commands) {
                result = c.match(cli);
                if (result != MatchResult.Failed)
                    return result;
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
        command.parentSet = this;
        commands.add(command);
    }
}
