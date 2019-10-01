package ua.training.controller.util;

import ua.training.model.entities.Taxpayer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Timestamp;

public class UserTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        Taxpayer taxpayer = (Taxpayer) pageContext.getSession().getAttribute("taxpayer");
        if (taxpayer != null) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String login = "Hello, " + taxpayer.getName() + "!";
            JspWriter out = pageContext.getOut();
            try {
                out.write(login);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SKIP_BODY;
    }
}
