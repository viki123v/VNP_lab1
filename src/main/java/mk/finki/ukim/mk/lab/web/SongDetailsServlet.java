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

@WebServlet("/song/details/*")
@AllArgsConstructor
public class SongDetailsServlet extends HttpServlet {

    private SpringTemplateEngine engine;
    private SongService songService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(
            JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req,resp)
        );
        String trackIdInPath=req.getPathInfo().substring(1);
        context.setVariable("song",songService.findByTrackId(trackIdInPath));
        engine.process(
            "songDetails",
            context,
            resp.getWriter()
        );
    }

}
