package ua.training.controller.commands.impl;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.controller.util.Config;
import ua.training.controller.util.Info;
import ua.training.logging.LoggerLoader;
import ua.training.model.entities.Inspector;
import ua.training.model.entities.Taxpayer;
import ua.training.model.services.InspectorService;
import ua.training.model.services.TaxpayerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private final Logger logger = LoggerLoader.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        int loginLength = login.length();
        if (loginLength == 6){
            Inspector inspector = InspectorService.getInstance().login(login, password);
            if (inspector == null){
                request.setAttribute("error", Info.getInstance().getProperty(Info.LOGIN_ERROR));
                return Config.getInstance().getProperty(Config.LOGIN);
            }
            inspector.getUser().setPassword(null);
            request.getSession().setAttribute("inspector", inspector);
            logger.info("Sign in inspector " + inspector.getUser().getLogin());
            return Config.getInstance().getProperty(Config.HOME);
        } else if (loginLength == 8 || loginLength == 10){
            Taxpayer taxpayer = TaxpayerService.getInstance().login(login,password);
            if (taxpayer == null){
                request.setAttribute("error", Info.getInstance().getProperty(Info.LOGIN_ERROR));
                return Config.getInstance().getProperty(Config.LOGIN);
            }
            taxpayer.getUser().setPassword(null);
            request.getSession().setAttribute("taxpayer", taxpayer);
            logger.info("Sign in taxpayer " + taxpayer.getUser().getLogin());
            return Config.getInstance().getProperty(Config.HOME);
        }else {
            request.setAttribute("error", Info.getInstance().getProperty(Info.LOGIN_ERROR));
            return Config.getInstance().getProperty(Config.LOGIN);
        }
    }
}
