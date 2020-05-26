package pro.gravit.launcher.toolbox.commands;

import pro.gravit.launcher.request.Request;
import pro.gravit.utils.command.Command;

public class AddLogListenerCommand extends Command {
    @Override
    public String getArgsDescription() {
        return "[]";
    }

    @Override
    public String getUsageDescription() {
        return "redirect LaunchServer output stream to toolbox";
    }

    @Override
    public void invoke(String... args) throws Exception {

    }
}
