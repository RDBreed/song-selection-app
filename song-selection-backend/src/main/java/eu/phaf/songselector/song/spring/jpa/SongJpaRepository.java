package eu.phaf.songselector.song.spring.jpa;

import eu.phaf.songselector.song.Song;
import eu.phaf.songselector.song.SongRepository;
import eu.phaf.songselector.song.spring.jpa.SongJpa;

import java.util.List;
import java.util.stream.Stream;

public class SongJpaRepository implements SongRepository {
  private final SongJpa songJpa;

  public SongJpaRepository(SongJpa songJpa) {
    this.songJpa = songJpa;
  }

  @Override
  public List<Song> findByTitleContainingIgnoreCase(String query) {
    return songJpa.findByTitleContainingIgnoreCase(query)
      .stream()
      .map(song -> new Song(song.getTitle(), song.getArtist()))
      .toList();
  }

  @Override
  public void add(Song song) {
    songJpa.save(new SongJpa.Song(song.title(), song.artist()));
  }

  @Override
  public Stream<Song> streamAll() {
    return songJpa.streamAll()
      .map(song -> new Song(song.getTitle(), song.getArtist()));
  }
}
