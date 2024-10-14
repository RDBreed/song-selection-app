package eu.phaf.songselector.song.csv;

import eu.phaf.songselector.song.CreateSongUseCase;
import eu.phaf.songselector.song.Song;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.io.Reader;

public class OpwekkingCsvReader {
  private final CreateSongUseCase createSongUseCase;
  private static final Logger LOG = LoggerFactory.getLogger(OpwekkingCsvReader.class);


  public OpwekkingCsvReader(CreateSongUseCase createSongUseCase) {
    this.createSongUseCase = createSongUseCase;
  }

  @PostConstruct
  public void init() {
    try {
      // Resource path to your songs.csv
      Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/opwekking.csv"));
      CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(';'));

      for (CSVRecord record : csvParser) {
        String nr = record.get("\uFEFFNr");
        String title = record.get("Beginregel");
        createSongUseCase.create(new Song(nr + " - " + title, "Opwekking"));
      }

    } catch (Exception e) {
      LOG.error("Failed to import csv", e);
    }
  }
}
