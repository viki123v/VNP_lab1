package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.services.interfaces.ArtistService;
import mk.finki.ukim.mk.lab.services.interfaces.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/artist")
@AllArgsConstructor
public class АrtistServlet extends HttpServlet {

    private SpringTemplateEngine springTemplateEngine;

    private ArtistService artistService;
    private SongService songService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = (String) req.getSession().getAttribute("trackId");

        if(trackId==null)
        {
            resp.sendRedirect("/listSongs");
            return;
        }

        WebContext context=new WebContext(
            JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp)
        );

        context.setVariable("trackId",trackId);
        context.setVariable("artists",artistService.listArtists());

        springTemplateEngine.process(
            "artistsList",
            context,
            resp.getWriter()
        );
    }

    private void cleanUpSession(HttpSession session){
        session.removeAttribute("trackId");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(session.getAttribute("trackId")==null){
            resp.sendRedirect("/listSongs");
            return;
        }

        Song song = songService.findByTrackId((String)session.getAttribute("trackId"));
        Artist artist=artistService.findById(
            Long.parseLong(
                req.getParameter("artistId")
            )
        );

        songService.addArtistToSong(artist,song);
        cleanUpSession(session);

        resp.sendRedirect("/song/details/" + song.getTrackId());
    }

}
