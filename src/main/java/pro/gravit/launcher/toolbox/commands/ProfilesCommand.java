package pro.gravit.launcher.toolbox.commands;

import pro.gravit.launcher.events.request.ProfilesRequestEvent;
import pro.gravit.launcher.profiles.ClientProfile;
import pro.gravit.launcher.request.update.ProfilesRequest;
import pro.gravit.launcher.toolbox.ToolboxMain;
import pro.gravit.utils.command.Command;
import pro.gravit.utils.helper.LogHelper;

public class ProfilesCommand extends Command {
    @Override
    public String getArgsDescription() {
        return "[]";
    }

    @Override
    public String getUsageDescription() {
        return "get profiles";
    }

    @Override
    public void invoke(String... args) throws Exception {
        ProfilesRequest request = new ProfilesRequest();
        ProfilesRequestEvent event = request.request(ToolboxMain.connectFactory.defaultInstance);
        event.profiles.forEach(this::printProfile);
        LogHelper.info("Profiles count: %d", event.profiles.size());
    }

    public void printProfile(ClientProfile profile)
    {
        LogHelper.info("Profile %s (%s) ", profile.getTitle(), profile.getUUID());
        LogHelper.subInfo("Minecraft version %s | addr %s:%d", profile.getVersion().toString(), profile.getServerAddress(), profile.getServerPort());
    }
}
