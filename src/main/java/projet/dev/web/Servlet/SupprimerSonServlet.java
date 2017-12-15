package projet.dev.web.Servlet;

import projet.dev.web.Managers.SonManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/supprimerSon")
public class SupprimerSonServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean supprValide= SonManager.getInstance().deleteSon(Integer.parseInt(req.getParameter("id")));
        resp.setStatus(supprValide?200:204);
    }
}
