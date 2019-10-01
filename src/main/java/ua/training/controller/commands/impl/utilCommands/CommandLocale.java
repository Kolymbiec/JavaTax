package ua.training.controller.commands.impl.utilCommands;

import ua.training.controller.commands.Command;
import ua.training.controller.util.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandLocale implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("locale", request.getParameter("locale"));
        return Config.getInstance().getProperty(Config.HOME);
}
}
