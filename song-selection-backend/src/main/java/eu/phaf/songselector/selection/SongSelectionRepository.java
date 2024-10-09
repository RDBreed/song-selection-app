package eu.phaf.songselector.selection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SongSelectionRepository {
  void create(SongSelection songSelection);

  SongSelection getSongSelectionForDate(LocalDate date);

  class SimpleSongSelectionRepository implements SongSelectionRepository {
    private final Map<LocalDate, List<SongSelected>> songSelectionPerDate = new HashMap<>();

    @Override
    public void create(SongSelection songSelection) {
      songSelectionPerDate.putIfAbsent(songSelection.date(), new ArrayList<>());
      for (String song : songSelection.songs()) {
        songSelectionPerDate.get(songSelection.date()).add(new SongSelected(song, songSelection.motivation()));
      }
    }

    @Override
    public SongSelection getSongSelectionForDate(LocalDate date) {
      List<SongSelected> songSelected = songSelectionPerDate.get(date);
      return new SongSelection(songSelected.stream().map(SongSelected::song).toList(), date, null);
    }
  }

  record SongSelected(String song, String motivation) {
  }
}
