package pro.gravit.launcher.toolbox.commands;

import pro.gravit.launcher.request.websockets.StdWebSocketService;
import pro.gravit.launcher.toolbox.ToolboxMain;
import pro.gravit.utils.command.Command;
import pro.gravit.utils.helper.LogHelper;

public class ConnectsCommand extends Command {
    @Override
    public String getArgsDescription() {
        return "[]";
    }

    @Override
    public String getUsageDescription() {
        return "Show connections";
    }

    @Override
    public void invoke(String... args) throws Exception {
        ToolboxMain.connectFactory.instances.forEach((key, service) -> {
            LogHelper.info("[%s] isConnected: %s", key, service.isClosed ? "false" : "true");
        });
        if(ToolboxMain.connectFactory.defaultInstance != null)
        {
            LogHelper.info("[DEFAULT] isConnected: %s", ToolboxMain.connectFactory.defaultInstance.isClosed ? "false" : "true");
        }
    }
}
