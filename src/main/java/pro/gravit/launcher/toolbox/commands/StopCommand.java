package pro.gravit.launcher.toolbox.commands;

import pro.gravit.utils.command.Command;

public class StopCommand extends Command {
    @Override
    public String getArgsDescription() {
        return "[]";
    }

    @Override
    public String getUsageDescription() {
        return "stop this toolbox and exit to shell";
    }

    @Override
    public void invoke(String... args) throws Exception {
        System.exit(0);
    }
}
