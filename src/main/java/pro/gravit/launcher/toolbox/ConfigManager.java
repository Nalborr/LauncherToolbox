package pro.gravit.launcher.toolbox;

import pro.gravit.launcher.config.JsonConfigurable;

import java.lang.reflect.Type;
import java.nio.file.Path;

public class ConfigManager extends JsonConfigurable<ToolboxConfig> {
    public ToolboxConfig config;

    public ConfigManager(Path configPath) {
        super(ToolboxConfig.class, configPath);
    }

    @Override
    public ToolboxConfig getConfig() {
        return config;
    }

    @Override
    public ToolboxConfig getDefaultConfig() {
        ToolboxConfig defaultConfig = new ToolboxConfig();
        return defaultConfig;
    }

    @Override
    public void setConfig(ToolboxConfig config) {
        this.config = config;
    }
}
