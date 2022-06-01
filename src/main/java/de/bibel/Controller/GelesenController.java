package de.bibel.Controller;

import de.bibel.application.Service.GelesenService;
import de.bibel.application.model.Gelesen;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class GelesenController {

  private final GelesenService gelesenService;

  @PostMapping("/gelesen")
  public ResponseEntity<GelesenResponseDTO> saveGelesen(
      @RequestBody GelesenRequestDTO gelesenRequestDTO) {
    return ResponseEntity.status(200).body(new GelesenResponseDTO(
        gelesenService.saveGelesen(gelesenRequestDTO),
        "Added new entry for text " + gelesenRequestDTO.bibelabschnitt));
  }

  @PostMapping("/gelesen/update")
  public ResponseEntity<GelesenResponseDTO> updateGelesen(@RequestBody GelesenRequestDTO gelesenRequestDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(new GelesenResponseDTO(gelesenService.updateGelesen(gelesenRequestDTO),
            "Updated entry for text " + gelesenRequestDTO.bibelabschnitt));
  }

  @GetMapping("/gelesen")
  public ResponseEntity<GelesenResponseDTO> getGelesen(
      @RequestParam (required = false) String bibelabschnitt,
      @RequestParam (required = false) String kommentarAusschnitt,
      @RequestParam (required = false) String leser,
      @RequestParam (required = false) String label,
      @RequestParam (required = false) String lieblingsvers,
      @RequestParam (required = false) String lieblingsversText
      ) {
    List<Gelesen> list = gelesenService.getGelesen(bibelabschnitt, kommentarAusschnitt, leser, label, lieblingsvers, lieblingsversText);
    return ResponseEntity.status(200).body(new GelesenResponseDTO(list, null));
  }
  
  @DeleteMapping("/gelesen")
  public ResponseEntity<GelesenResponseDTO> deleteMapping(@RequestParam String id) {
    return ResponseEntity.status(200)
        .body(new GelesenResponseDTO( gelesenService.deleteGelesen(UUID.fromString(id)), "Deleted entry with id " + id));
  }
}