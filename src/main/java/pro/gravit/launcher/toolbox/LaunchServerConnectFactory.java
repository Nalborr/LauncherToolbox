package pro.gravit.launcher.toolbox;

import pro.gravit.launcher.request.websockets.StdWebSocketService;

import javax.net.ssl.SSLException;
import java.util.HashMap;
import java.util.Map;

public class LaunchServerConnectFactory {
    public Map<String, StdWebSocketService> instances = new HashMap<>();
    public StdWebSocketService defaultInstance;
    private static boolean initRegisters = false;
    public StdWebSocketService newInstance(String host) throws Exception {
        StdWebSocketService ret = new StdWebSocketService(host);
        if(!initRegisters)
        {
            ret.registerResults();
            ret.registerRequests();
        }
        if(defaultInstance == null)
            defaultInstance = ret;
        ret.open();
        return ret;
    }
}
