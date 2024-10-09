package eu.phaf.songselector.song;

public class CreateSongUseCase {
  private final SongRepository songRepository;

  public CreateSongUseCase(SongRepository songRepository) {
    this.songRepository = songRepository;
  }

  public void create(Song song) {
    songRepository.add(song);
  }
}
