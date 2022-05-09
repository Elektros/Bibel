package de.bibel.application.Service;

import de.bibel.application.model.Gelesen;
import de.bibel.application.model.GelesenRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GelesenService {

  private static final Logger LOGGER = LogManager.getLogger(GelesenService.class);

  private final GelesenRepository gelesenRepository;

  public List<Gelesen> saveGelesen(
      String bibelabschnitt,
      List<String> lieblingsverse,
      List<String> versTexte,
      List<String> labels,
      String leser,
      String kommentar) {
    Map<String, String> lieblingsVerseMitText = new HashMap<>();
    for (String vers : lieblingsverse) {
      for (String versText : versTexte) {
        lieblingsVerseMitText.put(vers, versText);
      }
    }

    Gelesen gelesen =
        Gelesen.builder()
            .text(bibelabschnitt)
            .lieblingsvers(lieblingsVerseMitText)
            .label(labels)
            .leser(leser)
            .kommentar(kommentar)
            .build();

    gelesenRepository.save(gelesen);
    return gelesenRepository.findAll();
  }

  public List<Gelesen> getGelesen(
      String bibelabschnitt,
      String kommentarAusschnitt,
      String leser,
      String label,
      String lieblingsvers) {
    return gelesenRepository.findGelesensByLabelInAndLieblingsversIn(bibelabschnitt, kommentarAusschnitt, leser, label, lieblingsvers);
  }

  public List<Gelesen> deleteGelesen(UUID id) {
    Optional<Gelesen> gelesen = gelesenRepository.findById(id);
    gelesen.ifPresent(gelesenRepository::delete);
    return gelesenRepository.findAll();
  }
}