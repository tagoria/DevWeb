package projet.dev.web.Servlet;

import projet.dev.web.Dao.StreamerDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/streamers")
public class StremearsServlet extends  GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("listeStreamers",new StreamerDao().listeStreamer());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("lesStreamers", context, resp.getWriter());
    }
}
