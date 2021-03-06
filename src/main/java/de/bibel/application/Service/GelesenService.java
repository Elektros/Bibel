package de.bibel.application.Service;

import de.bibel.Controller.dto.GelesenRequestDTO;
import de.bibel.Controller.dto.GetListsResponseDto;
import de.bibel.Controller.dto.GetListsResultResponseDto;
import de.bibel.Controller.dto.UpdateRequestDto;
import de.bibel.application.model.Gelesen;
import de.bibel.application.model.GelesenRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
            .timestamp(LocalDateTime.now())
            .updatedTimestamp(LocalDateTime.now())
            .build();

    gelesenRepository.save(gelesen);
    return gelesenRepository.findAll();
  }

  public List<Gelesen> updateGelesen(UpdateRequestDto updateRequestDto) {
    List<Gelesen> updateEntry = new ArrayList<>();
    Optional<Gelesen> entryToUpdate = gelesenRepository.findById(UUID.fromString(updateRequestDto.getId()));
    if (entryToUpdate.isPresent()) {
      entryToUpdate.get().setBibelabschnitt(updateRequestDto.getBibelabschnitt());
      entryToUpdate.get().setLieblingsverse(updateRequestDto.getLieblingsverse());
      entryToUpdate.get().setVersText(updateRequestDto.getVersText());
      entryToUpdate.get().setLabels(updateRequestDto.getLabels());
      entryToUpdate.get().setLeser(updateRequestDto.getLeser());
      entryToUpdate.get().setKommentar(updateRequestDto.getKommentar());
      entryToUpdate.get().setUpdatedTimestamp(LocalDateTime.now());

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
      String lieblingsversText,
      LocalDateTime startDateTime,
      LocalDateTime endDateTime) {
    return gelesenRepository.findGelesenByLabelInAndLieblingsversIn(
        bibelabschnitt,
        kommentarAusschnitt,
        leser,
        label,
        lieblingsvers,
        lieblingsversText,
        startDateTime,
        endDateTime
    );
  }

  public GetListsResultResponseDto getAllGelesen() {
    List<Gelesen> allEntries = gelesenRepository.findAll();
    GetListsResultResponseDto response = new GetListsResultResponseDto();
    GetListsResponseDto list = new GetListsResponseDto();
    List<String> labels = allEntries
        .stream()
        .map(Gelesen::getLabels)
        .collect(Collectors.toList())
        .stream()
        .flatMap(Collection::stream)
        .distinct()
        .sorted()
        .collect(Collectors.toList());
    List<String> bibelabschnitte = allEntries
        .stream()
        .map(Gelesen::getBibelabschnitt)
        .distinct()
        .sorted()
        .collect(Collectors.toList());
    List<String> lieblingsverse = allEntries
        .stream()
        .map(Gelesen::getLieblingsverse)
        .collect(Collectors.toList())
        .stream()
        .flatMap(Collection::stream)
        .distinct()
        .sorted()
        .collect(Collectors.toList());
    list.setLabels(labels);
    list.setBibelabschnitte(bibelabschnitte);
    list.setLieblingsverse(lieblingsverse);
    response.setResult(list);
    return response;
  }

  public List<Gelesen> deleteGelesen(UUID id) {
    Optional<Gelesen> gelesen = gelesenRepository.findById(id);
    gelesen.ifPresent(gelesenRepository::delete);
    return gelesenRepository.findAll();
  }
}