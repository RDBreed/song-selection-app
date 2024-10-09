package eu.phaf.songselector.song;

import java.util.List;
import java.util.stream.Stream;

public interface SongRepository {
  List<Song> findByTitleContainingIgnoreCase(String query);

  void add(Song song);

  Stream<Song> streamAll();
}
