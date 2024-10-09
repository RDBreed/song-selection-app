package eu.phaf.songselector.dates.spring;

import eu.phaf.songselector.dates.CreateDateUseCase;
import eu.phaf.songselector.dates.DateRepository;
import eu.phaf.songselector.dates.GetAvailableDatesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatesConfiguration {
  @Bean
  public DateRepository dateRepository() {
    return new DateRepository.SimpleDateRepository();
  }

  @Bean
  public GetAvailableDatesUseCase getAvailableDatesUseCase() {
    return new GetAvailableDatesUseCase(dateRepository());
  }

  @Bean
  public CreateDateUseCase createDateUseCase() {
    return new CreateDateUseCase(dateRepository());
  }
}
