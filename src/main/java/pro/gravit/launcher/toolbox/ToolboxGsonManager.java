package pro.gravit.launcher.toolbox;

import com.google.gson.GsonBuilder;
import pro.gravit.launcher.managers.GsonManager;
import pro.gravit.launcher.request.auth.AuthRequest;
import pro.gravit.launcher.request.websockets.ClientWebSocketService;

public class ToolboxGsonManager extends GsonManager {
    @Override
    public void registerAdapters(GsonBuilder builder) {
        ClientWebSocketService.appendTypeAdapters(builder);
        AuthRequest.registerProviders();
        super.registerAdapters(builder);
    }
}
