package de.bibel.application.Service;

import de.bibel.Controller.dto.GelesenRequestDTO;
import de.bibel.Controller.dto.UpdateRequestDto;
import de.bibel.application.model.Gelesen;
import de.bibel.application.model.GelesenRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GelesenService {

  private static final Logger LOGGER = LogManager.getLogger(GelesenService.class);

  protected final GelesenRepository gelesenRepository;

  public List<Gelesen> saveGelesen(GelesenRequestDTO gelesenRequestDTO) {
    Gelesen gelesen =
        Gelesen.builder()
            .bibelabschnitt(gelesenRequestDTO.getBibelabschnitt())
            .lieblingsverse(gelesenRequestDTO.getLieblingsverse())
            .versText(gelesenRequestDTO.getVersText())
            .labels(gelesenRequestDTO.getLabels())
            .leser(gelesenRequestDTO.getLeser())
            .kommentar(gelesenRequestDTO.getKommentar())
            .build();

    gelesenRepository.save(gelesen);
    return gelesenRepository.findAll();
  }

  public List<Gelesen> updateGelesen(UpdateRequestDto updateRequestDto) {
    List<Gelesen> updateEntry = new ArrayList<>();
    Optional<Gelesen> entryToUpdate = gelesenRepository.findById(UUID.fromString(updateRequestDto.getId()));
    if (entryToUpdate.isPresent()) {
      entryToUpdate.get().setLieblingsverse(updateRequestDto.getLieblingsverse());
      entryToUpdate.get().setVersText(updateRequestDto.getVersText());
      entryToUpdate.get().setLabels(updateRequestDto.getLabels());
      entryToUpdate.get().setLeser(updateRequestDto.getLeser());
      entryToUpdate.get().setKommentar(updateRequestDto.getKommentar());

      gelesenRepository.save(entryToUpdate.get());
      updateEntry.add(entryToUpdate.get());
      return updateEntry;
    }
    return null;
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