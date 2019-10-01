package ua.training.controller.servlets;

import org.apache.log4j.Logger;
import ua.training.controller.commands.Command;
import ua.training.logging.LoggerLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class Controller extends HttpServlet {
    private final Logger logger = LoggerLoader.getLogger(Controller.class);

    private ControllerHelper controllerHelper = ControllerHelper.getInstance();

    private void processRequest(HttpServletRequest request, HttpServletResponse response){
        String page;
        Command command = controllerHelper.getCommand(request);
        page = command.execute(request, response);
        try {
            if (page.equals("/index.jsp")){
                response.sendRedirect("/");
            }else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        }catch (ServletException | IOException e){
            logger.error(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        processRequest(request, response);
    }


}
