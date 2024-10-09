package eu.phaf.songselector.song.spring;

import eu.phaf.songselector.song.CreateSongUseCase;
import eu.phaf.songselector.song.GetAllSongsUseCase;
import eu.phaf.songselector.song.SearchSongUseCase;
import eu.phaf.songselector.song.SongRepository;
import eu.phaf.songselector.song.csv.OpwekkingCsvReader;
import eu.phaf.songselector.song.spring.jpa.SongJpa;
import eu.phaf.songselector.song.spring.jpa.SongJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SongConfiguration {

  @Bean
  public SongRepository songRepository(SongJpa songJpa) {
    return new SongJpaRepository(songJpa);
  }

  @Bean
  public CreateSongUseCase createSongUseCase(SongRepository songRepository) {
    return new CreateSongUseCase(songRepository);
  }

  @Bean
  public OpwekkingCsvReader opwekkingCsvReader(SongRepository songRepository) {
    return new OpwekkingCsvReader(createSongUseCase(songRepository));
  }

  @Bean
  public SearchSongUseCase searchSongUseCase(SongRepository songRepository) {
    return new SearchSongUseCase(songRepository);
  }

  @Bean
  public GetAllSongsUseCase getAllSongsUseCase(SongRepository songRepository) {
    return new GetAllSongsUseCase(songRepository);
  }
}
