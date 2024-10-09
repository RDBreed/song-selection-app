package eu.phaf.songselector.selection;

import java.time.LocalDate;
import java.util.List;

public record SongSelection(List<String> songs, LocalDate date, String motivation) {
}
