package projet.dev.web.Servlet;

import projet.dev.web.Entities.Son;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import projet.dev.web.Managers.FichierManager;
import projet.dev.web.Managers.SonManager;
import projet.dev.web.Managers.StreamerManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@MultipartConfig
@WebServlet("/modifierSon")
public class ModifierSonServlet extends  GenericServlet {
    private int idSon;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        idSon=Integer.parseInt(req.getParameter("id"));
        context.setVariable("listeStreamers", StreamerManager.getInstance().getStreamers());
        context.setVariable("son", SonManager.getInstance().getSon(idSon));
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("modifierSon", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idStreamer = Integer.parseInt(req.getParameter("streamer"));
        Part sonPart = req.getPart("fichierAudio");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Son son= new Son(req.getParameter("description"),
                idSon,
                LocalDate.parse(req.getParameter("date"),dateFormat),
        SonManager.getInstance().getSon(idSon).getCheminSon(),
                StreamerManager.getInstance().getStreamer(idStreamer
        ),false);
        try {
            FichierManager.getInstance().modifyExistingSon(son,sonPart);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("soundboard");
    }
}
