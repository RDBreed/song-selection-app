package eu.phaf.songselector.dates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface DateRepository {

  void createDate(LocalDate localDate);

  List<LocalDate> getAvailableDates();

  List<LocalDate> getAllDates();

  void closeDate(LocalDate localDate);

  class SimpleDateRepository implements DateRepository {
    private final List<Date> availableDates = new ArrayList<>();

    public SimpleDateRepository() {
      // Voeg enkele datums toe
      availableDates.add(new Date(LocalDate.of(2024, 10, 3), true));
      availableDates.add(new Date(LocalDate.of(2024, 10, 10), true));
    }

    @Override
    public void createDate(LocalDate localDate) {
      availableDates.add(new Date(localDate, true));
    }

    @Override
    public List<LocalDate> getAvailableDates() {
      return availableDates
        .stream()
        .filter(Date::isOpen)
        .map(Date::date)
        .collect(Collectors.toList());
    }

    @Override
    public List<LocalDate> getAllDates() {
      return availableDates
        .stream().map(Date::date)
        .collect(Collectors.toList());
    }

    @Override
    public void closeDate(LocalDate localDate) {
      Date theDate = availableDates.stream().filter(date -> date.date().equals(localDate)).findFirst().orElseThrow(() -> new RuntimeException("No date %s found".formatted(localDate)));
      availableDates.remove(theDate);
      availableDates.add(new Date(theDate.date(), false));
    }

    public record Date(LocalDate date, boolean isOpen) {
    }
  }
}
