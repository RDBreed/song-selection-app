package eu.phaf.songselector.song;

import java.util.stream.Stream;

public class GetAllSongsUseCase {
  private final SongRepository songRepository;

  public GetAllSongsUseCase(SongRepository songRepository) {
    this.songRepository = songRepository;
  }

  public Stream<Song> findAll() {
    return songRepository.streamAll();
  }
}
