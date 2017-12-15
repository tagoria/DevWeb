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
import java.time.format.DateTimeParseException;

@MultipartConfig
@WebServlet("/ajouterSon")
public class AjouterSonServlet extends GenericServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("listeStreamers", StreamerManager.getInstance().getStreamers());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("ajouterSon", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String streamer = req.getParameter("streamer");
        String dateString = req.getParameter("date");
        String description = req.getParameter("description");
        if (streamer != null && dateString != null && description != null) {
            try {

                int idStreamer = Integer.parseInt(streamer);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(dateString, dateFormat);
                Part sonPart = req.getPart("fichierAudio");
                Son son = new Son(description,
                            0,
                            date,
                            "", StreamerManager.getInstance().getStreamer(idStreamer)
                            , false);
                try {
                    son=FichierManager.getInstance().saveNewSon(son,sonPart);
                    SonManager.getInstance().addSon(son);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resp.sendRedirect("ajouterSon");
            } catch (DateTimeParseException | IOException | NumberFormatException d) {
                d.printStackTrace();
            }
        }
    }
}