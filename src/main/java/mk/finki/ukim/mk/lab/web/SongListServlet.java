package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.services.interfaces.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/listSongs")
@AllArgsConstructor
public class SongListServlet extends HttpServlet {

    private SongService songService;
    private SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(
            JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp)
        );
        context.setVariable("songs",songService.listSongs());
        springTemplateEngine.process(
            "listSongs",
            context,
            resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession(true).setAttribute("trackId",req.getParameter("trackId"));
        resp.sendRedirect("/artist");
    }

}
