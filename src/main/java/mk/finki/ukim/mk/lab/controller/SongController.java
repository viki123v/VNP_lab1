package mk.finki.ukim.mk.lab.controller;

import mk.finki.ukim.mk.lab.dtos.SongDTO;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.services.impl.AlbumServiceImpl;
import mk.finki.ukim.mk.lab.services.interfaces.AlbumService;
import mk.finki.ukim.mk.lab.services.interfaces.SongService;
import mk.finki.ukim.mk.lab.services.impl.SongServiceImpl;
import mk.finki.ukim.mk.lab.util.SongMapper;
import mk.finki.ukim.mk.lab.util.SongMapperImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class SongController {

    private SongService songService;
    private AlbumService albumService;
    private SongMapper songMapper;

    public SongController(
        SongServiceImpl songService,
        AlbumServiceImpl albumService,
        SongMapperImpl songMapper
    ) {
        this.songService = songService;
        this.albumService = albumService;
        this.songMapper=songMapper;
    }

    @GetMapping("/listSongs")
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("songs", songService.listSongs());
        model.addAttribute("albums",albumService.findAll());
        return "listSongs";
    }

    @PostMapping("/songs/add")
    public String saveSong(
        @ModelAttribute SongDTO dto
    ) {
        songService.save(dto);
        return "redirect:/listSongs";
    }

    @PostMapping("/songs/edit/{songId}")
    public String editSong(
        @PathVariable Long songId,
        @ModelAttribute SongDTO songDTO
    ) {
        songService.updateSong(songId,songDTO);
        return "redirect:/listSongs";
    }

    @GetMapping("/songs/edit-form/{songId}")
    public String getEditSongForm(@PathVariable Long songId, Model model) {
        Optional<Song> song = songService.findById(songId);

        if (song.isEmpty())
            return "redirect:/listSongs";

        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("song",
                           songMapper.dto(song.get())
        );
        model.addAttribute("isNew",false) ;

        return "add-song";
    }

    @GetMapping("/songs/add-form")
    public String getAddSongPage(Model model){
        model.addAttribute("song",new SongDTO());
        model.addAttribute("albums",albumService.findAll());
        model.addAttribute("isNew",true);
        return "add-song";
    }

    @PostMapping("songs/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.removeSongById(id);
        return "redirect:/listSongs";
    }

}
