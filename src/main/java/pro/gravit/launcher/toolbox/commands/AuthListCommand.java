package pro.gravit.launcher.toolbox.commands;

import pro.gravit.launcher.events.request.GetAvailabilityAuthRequestEvent;
import pro.gravit.launcher.request.auth.GetAvailabilityAuthRequest;
import pro.gravit.launcher.toolbox.ToolboxMain;
import pro.gravit.utils.command.Command;
import pro.gravit.utils.helper.LogHelper;

public class AuthListCommand extends Command {
    @Override
    public String getArgsDescription() {
        return "[]";
    }

    @Override
    public String getUsageDescription() {
        return "get auth list";
    }

    @Override
    public void invoke(String... args) throws Exception {
        GetAvailabilityAuthRequest availabilityAuthRequest = new GetAvailabilityAuthRequest();
        GetAvailabilityAuthRequestEvent event = availabilityAuthRequest.request(ToolboxMain.connectFactory.defaultInstance);
        LogHelper.info("Features: %d", event.features);
        event.list.forEach(this::printAuthAvailability);
    }

    public void printAuthAvailability(GetAvailabilityAuthRequestEvent.AuthAvailability authAvailability)
    {
        LogHelper.info("AuthAvailability %s (name: %s)", authAvailability.displayName, authAvailability.name);
        LogHelper.subInfo("FirstType: %s | SecondType: %s", authAvailability.firstType, authAvailability.secondType);
    }
}
