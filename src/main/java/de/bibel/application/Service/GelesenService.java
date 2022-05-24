package de.bibel.application.Service;

import de.bibel.Controller.GelesenRequestDTO;
import de.bibel.application.model.Gelesen;
import de.bibel.application.model.GelesenRepository;
import java.util.List;
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

  public List<Gelesen> saveGelesen(GelesenRequestDTO gelesenRequestDTO) {
    Gelesen gelesen =
        Gelesen.builder()
            .text(gelesenRequestDTO.getBibelabschnitt())
            .lieblingsvers(gelesenRequestDTO.getLieblingsverse())
            .lieblingsversText(gelesenRequestDTO.getVersText())
            .label(gelesenRequestDTO.getLabels())
            .leser(gelesenRequestDTO.getLeser())
            .kommentar(gelesenRequestDTO.getKommentar())
            .build();

    gelesenRepository.save(gelesen);
    return gelesenRepository.findAll();
  }

  public List<Gelesen> getGelesen(
      String bibelabschnitt,
      String kommentarAusschnitt,
      String leser,
      String label,
      String lieblingsvers,
      String lieblingsversText) {
    return gelesenRepository.findGelesensByLabelInAndLieblingsversIn(
        bibelabschnitt,
        kommentarAusschnitt,
        leser,
        label,
        lieblingsvers,
        lieblingsversText
    );
  }

  public List<Gelesen> deleteGelesen(UUID id) {
    Optional<Gelesen> gelesen = gelesenRepository.findById(id);
    gelesen.ifPresent(gelesenRepository::delete);
    return gelesenRepository.findAll();
  }
}