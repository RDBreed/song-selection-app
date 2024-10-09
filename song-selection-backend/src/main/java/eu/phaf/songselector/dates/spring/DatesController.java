package eu.phaf.songselector.dates.spring;

import eu.phaf.songselector.dates.GetAvailableDatesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DatesController {
  private final GetAvailableDatesUseCase getAvailableDatesUseCase;

  public DatesController(GetAvailableDatesUseCase getAvailableDatesUseCase) {
    this.getAvailableDatesUseCase = getAvailableDatesUseCase;
  }

  // Endpoint voor het ophalen van beschikbare datums
  @GetMapping("/dates")
  public ResponseEntity<List<LocalDate>> getAvailableDates() {
    List<LocalDate> dates = getAvailableDatesUseCase.getAllAvailableDates();
    return ResponseEntity.ok(dates);
  }
}
