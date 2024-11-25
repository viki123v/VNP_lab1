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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class ArtistController {


    private ArtistService artistService;
    private SongService songService;

    @GetMapping("/artist")
    protected String doGet(
        @SessionAttribute Optional<String> trackId,
        Model model
    ){
        return trackId.map(trackIdVal -> {
            model.addAttribute("trackId",trackIdVal);
            model.addAttribute("artists",artistService.listArtists());
            return "artistsList";
        }).orElse("redirect:/listSongs");
    }


    @PostMapping("/artist")
    protected String doPost(
        @SessionAttribute Optional<String> trackId,
        @RequestParam Long artistId,
        HttpSession session
    ){
        return trackId.map(trackIdVal -> {
            songService.addArtistToSong(artistId,trackIdVal);

            session.removeAttribute("trackId");
            return "redirect:/song/details/" + trackIdVal;
        }).orElse("redirect:/listSongs");
    }

}
