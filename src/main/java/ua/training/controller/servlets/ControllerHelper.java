package ua.training.controller.servlets;

import ua.training.controller.commands.Command;
import ua.training.controller.commands.impl.LoginCommand;
import ua.training.controller.commands.impl.utilCommands.CommandLocale;
import ua.training.controller.commands.impl.utilCommands.CommandMissing;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ControllerHelper {

    private static ControllerHelper controllerHelper;
    private HashMap<String, Command> commands = new HashMap<>();

    private ControllerHelper(){
        commands.put("login", new LoginCommand());

        //commands.put("register", new CommandRegister());
        commands.put("locale", new CommandLocale());
        commands.put("missing", new CommandMissing());
    }

    public static ControllerHelper getInstance() {
        if (controllerHelper == null) {
            controllerHelper = new ControllerHelper();
        }
        return controllerHelper;
    }

    public Command getCommand(HttpServletRequest request){
        Command command = commands.get(request.getParameter("command"));
        if (command == null){
            command = new CommandMissing();
        }
        return command;
    }
}
