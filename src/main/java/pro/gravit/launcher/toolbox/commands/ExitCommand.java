package pro.gravit.launcher.toolbox.commands;

import pro.gravit.launcher.events.request.ExitRequestEvent;
import pro.gravit.launcher.request.auth.ExitRequest;
import pro.gravit.launcher.toolbox.ToolboxMain;
import pro.gravit.utils.command.Command;
import pro.gravit.utils.helper.LogHelper;

public class ExitCommand extends Command {
    @Override
    public String getArgsDescription() {
        return "[true/false] (username)";
    }

    @Override
    public String getUsageDescription() {
        return "exit from account(or kick other account)";
    }

    @Override
    public void invoke(String... args) throws Exception {
        verifyArgs(args, 1);
        boolean isExitAll = Boolean.parseBoolean(args[0]);
        ExitRequest exitRequest;
        if(args.length > 1) exitRequest = new ExitRequest(isExitAll, args[1]);
        else exitRequest = new ExitRequest(isExitAll);
        ExitRequestEvent event = exitRequest.request(ToolboxMain.connectFactory.defaultInstance);
        LogHelper.info("Successful. Exit reason: %s", event.reason);
    }
}
