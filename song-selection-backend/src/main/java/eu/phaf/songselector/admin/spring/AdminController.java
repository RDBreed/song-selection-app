package eu.phaf.songselector.admin.spring;

import eu.phaf.songselector.dates.CreateDateUseCase;
import eu.phaf.songselector.selection.GetSongsForDateUseCase;
import eu.phaf.songselector.selection.SongSelection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

  private final CreateDateUseCase createDateUseCase;
  private final GetSongsForDateUseCase getSongsForDateUseCase;

  public AdminController(CreateDateUseCase createDateUseCase, GetSongsForDateUseCase getSongsForDateUseCase) {
    this.createDateUseCase = createDateUseCase;
    this.getSongsForDateUseCase = getSongsForDateUseCase;
  }

  @PostMapping("/dates")
  public ResponseEntity<?> addDate(@RequestBody DateRequest body) {
    if (body.date() == null) {
      return ResponseEntity.badRequest().body("Invalid date");
    }
    boolean success = createDateUseCase.createDate(body.date());
    if (success) {
      return ResponseEntity.status(201).build();
    } else {
      return ResponseEntity.status(500).build();
    }
  }

  @GetMapping("/songs-for-date")
  public ResponseEntity<List<SongsSelected>> getSongsForDate(@RequestParam("date") LocalDate date) {
    if (date == null) {
      return ResponseEntity.badRequest().build();
    }
    SongSelection songs = getSongsForDateUseCase.getSongSelectionForDate(date);
    List<SongsSelected> countedSongs =
      songs.songs()
        .stream()
        .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
        .entrySet()
        .stream()
        .map(songWithCount -> new SongsSelected(songWithCount.getKey(), songWithCount.getValue()))
        .toList();
    return ResponseEntity.ok(countedSongs);
  }

  public record DateRequest(LocalDate date) {
  }

  public record SongsSelected(String title, long timesChosen) {
  }
}
