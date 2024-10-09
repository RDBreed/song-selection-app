package eu.phaf.songselector.selection.spring;

import eu.phaf.songselector.dates.GetAvailableDatesUseCase;
import eu.phaf.songselector.selection.CreateSongSelectionUseCase;
import eu.phaf.songselector.selection.GetSongsForDateUseCase;
import eu.phaf.songselector.selection.SongSelectionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SongSelectionConfiguration {
  @Bean
  public SongSelectionRepository songSelectionRepository() {
    return new SongSelectionRepository.SimpleSongSelectionRepository();
  }

  @Bean
  public CreateSongSelectionUseCase createSongSelectionUseCase(GetAvailableDatesUseCase getAvailableDatesUseCase) {
    return new CreateSongSelectionUseCase(songSelectionRepository(), getAvailableDatesUseCase);
  }

  @Bean
  public GetSongsForDateUseCase getSongsForDateUseCase(){
    return new GetSongsForDateUseCase(songSelectionRepository());
  }
}
