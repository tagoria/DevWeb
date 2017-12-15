package projet.dev.web.Servlet;

import projet.dev.web.Entities.Streamer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import projet.dev.web.Managers.StreamerManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
@WebServlet("/ajouterStreamer")
@MultipartConfig
public class AjouterStreamerServlet extends  GenericServlet {
    private WebContext context;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("messageStatutAjout", req.getSession().getAttribute("messageStatutAjout"));
        req.getSession().setAttribute("messageStatutAjout", null);
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("ajouterStreamer", context, resp.getWriter());
        req.getSession().setAttribute("messageStatutAjout", null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomStreamer = req.getParameter("nomStreamer");
        String cheminImage = "Images/" + nomStreamer + ".jpg";
        String description = req.getParameter("description");
        if (nomStreamer != null && description != null && StreamerManager.getInstance().getStreamerByName(nomStreamer) == null) {
            Streamer streamer = new Streamer(0, description, cheminImage, nomStreamer);
            StreamerManager.getInstance().addStreamer(streamer);
            Part imagePart = req.getPart("fichierImage");
            imagePart.write("C:/Users/Tagoria/Desktop/ProjetDevWeb/ProjetDevWeb/src/main/webapp/" + cheminImage);
            req.getSession().setAttribute("messageStatutAjout", "Streamer ajouté avec succés");

        } else {
            if (StreamerManager.getInstance().getStreamerByName(nomStreamer) != null) {
                req.getSession().setAttribute("messageStatutAjout", "Erreur le streamer est déjà dans la base de données");
            } else {
                req.getSession().setAttribute("messageStatutAjout", "Erreur certains champs étaient vides");
            }
        }
        resp.sendRedirect("ajouterStreamer");
    }
}
