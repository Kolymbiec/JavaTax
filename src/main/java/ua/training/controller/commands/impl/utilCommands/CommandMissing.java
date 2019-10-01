package ua.training.controller.commands.impl.utilCommands;

import ua.training.controller.commands.Command;
import ua.training.controller.util.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandMissing implements Command {

    //@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getRequestURI();
        switch (path){
            case("/login"):
                return Config.getInstance().getProperty(Config.LOGIN);
            case ("/register"):
                return Config.getInstance().getProperty(Config.LOGIN);
            default:
                return Config.getInstance().getProperty(Config.LOGIN);
        }
    }
}
