package eu.phaf.songselector.selection.spring;

import eu.phaf.songselector.selection.CreateSongSelectionUseCase;
import eu.phaf.songselector.selection.SongSelection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = {"${settings.cors_origin}"})
public class SongSelectionController {
  private final CreateSongSelectionUseCase createSongSelectionUseCase;

  public SongSelectionController(CreateSongSelectionUseCase createSongSelectionUseCase) {
    this.createSongSelectionUseCase = createSongSelectionUseCase;
  }

  // Endpoint voor het indienen van liedkeuzes voor een datum
  @PostMapping("/submit-songs")
  public ResponseEntity<Void> submitSongs(@RequestBody SongSelectionRequest songSelectionRequest) {
    boolean success = createSongSelectionUseCase.create(new SongSelection(songSelectionRequest.songs(), songSelectionRequest.date(), songSelectionRequest.motivation()));
    if (success) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  public record SongSelectionRequest(List<String> songs, LocalDate date, String motivation) {
  }

}
