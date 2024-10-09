package eu.phaf.songselector.song.spring.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.QueryHint;
import jakarta.persistence.Table;
import org.hibernate.jpa.AvailableHints;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

import static eu.phaf.songselector.song.spring.jpa.SongJpa.Song;

@Repository
@EntityScan
public interface SongJpa extends JpaRepository<Song, Long> {
  List<Song> findByTitleContainingIgnoreCase(String title);

  @Query("""
    select s
    from SongEntity s
    """
  )
  @QueryHints(
    @QueryHint(name = AvailableHints.HINT_FETCH_SIZE, value = "25")
  )
  Stream<Song> streamAll();


  @Entity(name = "SongEntity")
  public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String artist;

    public Song() {
    }

    public Song(String title, String artist) {
      this.title = title;
      this.artist = artist;
    }

    // Getters and Setters

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getArtist() {
      return artist;
    }

    public void setArtist(String artist) {
      this.artist = artist;
    }
  }

}
