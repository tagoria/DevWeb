package projet.dev.web.Servlet;

import projet.dev.web.Dao.SonDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import projet.dev.web.Entities.Son;
import projet.dev.web.Managers.SonManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/soundboard")
public class SoundboardServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext =req.getServletContext();
        WebContext context = new WebContext(req, resp, servletContext);
        SonDao sonDao = new SonDao();
        List<Son> sonList = SonManager.getInstance().getSons();
        context.setVariable("listeSon", sonList);
        TemplateEngine templateEngine = createTemplateEngine(servletContext);
        servletContext.setAttribute("servletAuth",this.getServletName());
        templateEngine.process("soundboard", context, resp.getWriter());

    }
}
