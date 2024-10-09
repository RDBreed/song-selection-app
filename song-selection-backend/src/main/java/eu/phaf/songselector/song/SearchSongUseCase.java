package eu.phaf.songselector.song;

import java.util.List;

public class SearchSongUseCase {
  private final SongRepository songRepository;

  public SearchSongUseCase(SongRepository songRepository) {
    this.songRepository = songRepository;
  }

  public List<Song> searchSongs(String query) {
    return songRepository.findByTitleContainingIgnoreCase(query);
  }

}
