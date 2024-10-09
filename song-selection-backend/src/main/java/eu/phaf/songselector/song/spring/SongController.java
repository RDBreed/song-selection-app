package eu.phaf.songselector.song.spring;

import eu.phaf.songselector.song.GetAllSongsUseCase;
import eu.phaf.songselector.song.SearchSongUseCase;
import eu.phaf.songselector.song.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = {"${settings.cors_origin}"})
public class SongController {
  private final SearchSongUseCase searchSongUseCase;
  private final GetAllSongsUseCase getAllSongsUseCase;

  @Autowired
  public SongController(SearchSongUseCase searchSongUseCase, GetAllSongsUseCase getAllSongsUseCase) {
    this.searchSongUseCase = searchSongUseCase;
    this.getAllSongsUseCase = getAllSongsUseCase;
  }

  // Endpoint voor het zoeken naar liederen op basis van een zoekterm
  @GetMapping("songs/search")
  public ResponseEntity<List<Song>> searchSongs(@RequestParam("q") String query) {
    if (query == null || query.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    List<Song> results = searchSongUseCase.searchSongs(query);
    return ResponseEntity.ok(results);
  }

  // Endpoint voor het ophalen van alle beschikbare liederen
  @GetMapping
  public ResponseEntity<Stream<Song>> getAllSongs() {
    Stream<Song> songs = getAllSongsUseCase.findAll();
    return ResponseEntity.ok(songs);
  }
}
