package pro.gravit.launcher.toolbox;

import org.apache.commons.cli.*;
import pro.gravit.launcher.Launcher;
import pro.gravit.launcher.toolbox.commands.*;
import pro.gravit.utils.command.CommandHandler;
import pro.gravit.utils.command.JLineCommandHandler;
import pro.gravit.utils.command.basic.ClearCommand;
import pro.gravit.utils.command.basic.DebugCommand;
import pro.gravit.utils.command.basic.GCCommand;
import pro.gravit.utils.command.basic.HelpCommand;
import pro.gravit.utils.helper.JVMHelper;
import pro.gravit.utils.helper.LogHelper;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ToolboxMain {
    public static ConfigManager configManager;
    public static CommandHandler commandHandler;
    public static LaunchServerConnectFactory connectFactory;
    public static void main(String[] args) throws Exception
    {
        LogHelper.printVersion("Toolbox");
        LogHelper.printLicense("Toolbox");
        if(args.length == 0)
        {
            String message = String.format("Arguments not found. Usage '%s --help' for help", String.join(" ", JVMHelper.RUNTIME_MXBEAN.getInputArguments()));
            LogHelper.error(message);
            try {
                JOptionPane.showMessageDialog(null, message, "GravitLauncher Toolbox", JOptionPane.ERROR_MESSAGE);
            } catch (Throwable ignored)
            {

            }
            System.exit(0);
        }
        Options options = new Options();
        options.addOption("c", "config-path", true, "Configuration path");
        options.addOption("m", "memory-config",false, "Memory configuration(no config file)");
        options.addOption("h", "host", true, "Websocket address for launchserver");
        options.addOption("i", "interactive", false, "Interactive mode(console input)");
        options.addOption("e", "exec", true, "Execute command");
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        Launcher.gsonManager = new ToolboxGsonManager();
        Launcher.gsonManager.initGson();
        try {
            cmd = parser.parse(options, args);
            if(!cmd.hasOption('m'))
            {
                Path configPath = Paths.get("config.json");
                //if(cmd.hasOption('c')) configPath = Paths.get(cmd.getOptionValue('c'));
                configManager = new ConfigManager(configPath);
                configManager.loadConfig();
            }
            else
            {
                configManager = new ConfigManager(null);
                configManager.setConfig( configManager.getDefaultConfig() );
            }
            if(cmd.hasOption('i'))
            {
                commandHandler = new JLineCommandHandler();
                registerCommands();
            }
            connectFactory = new LaunchServerConnectFactory();
            if(cmd.hasOption('h'))
            {
                connectFactory.newInstance(cmd.getOptionValue('h'));
                LogHelper.info("Success connection");
            }
            if(commandHandler != null)
            {
                commandHandler.run();
            }
            else
            {
                System.exit(0);
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("GravitLaunchr Toolbox", options);
            System.exit(1);
        }
    }
    public static void registerCommands()
    {
        commandHandler.registerCommand("debug", new DebugCommand());
        commandHandler.registerCommand("help", new HelpCommand(commandHandler));
        commandHandler.registerCommand("gc", new GCCommand());
        commandHandler.registerCommand("clear", new ClearCommand(commandHandler));
        commandHandler.registerCommand("connects", new ConnectsCommand());
        commandHandler.registerCommand("auth", new AuthCommand());
        commandHandler.registerCommand("profiles", new ProfilesCommand());
        commandHandler.registerCommand("authList", new AuthListCommand());
        commandHandler.registerCommand("exit", new ExitCommand());
        commandHandler.registerCommand("stop", new StopCommand());
    }
}
