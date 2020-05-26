package pro.gravit.launcher.toolbox.commands;

import pro.gravit.launcher.events.request.AuthRequestEvent;
import pro.gravit.launcher.request.auth.AuthRequest;
import pro.gravit.launcher.toolbox.LaunchServerConnectFactory;
import pro.gravit.launcher.toolbox.ToolboxMain;
import pro.gravit.utils.command.Command;
import pro.gravit.utils.helper.LogHelper;

public class AuthCommand extends Command {
    @Override
    public String getArgsDescription() {
        return "[login] [password] [auth_id]";
    }

    @Override
    public String getUsageDescription() {
        return "auth with login and password";
    }

    @Override
    public void invoke(String... args) throws Exception {
        verifyArgs(args, 3);
        AuthRequest request = new AuthRequest(args[0], args[1], args[2], AuthRequest.ConnectTypes.API);
        AuthRequestEvent event = request.request(ToolboxMain.connectFactory.defaultInstance);
        LogHelper.info("Auth success. AccessToken: %s, %s", event.accessToken, event.permissions.toString());
        if(event.playerProfile != null) LogHelper.info("PlayerProfile: username: %s uuid: %s", event.playerProfile.username, event.playerProfile.username, event.playerProfile.uuid);
    }
}
