package de.bibel.Controller;

import de.bibel.application.Service.GelesenService;
import de.bibel.application.model.Gelesen;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class GelesenController {

  private final GelesenService gelesenService;

  @PostMapping("/gelesen")
  public ResponseEntity<GelesenResponseDTO> saveGelesen(
      @RequestParam String bibelabschnitt,
      @RequestParam List<String> lieblingsverse,
      @RequestParam List<String> versText,
      @RequestParam List<String> labels,
      @RequestParam String kommentar,
      @RequestParam String leser) {
    return ResponseEntity.status(200).body(new GelesenResponseDTO(
        gelesenService.saveGelesen(bibelabschnitt, lieblingsverse, versText, labels, leser, kommentar),
        "Added new entry for text " + bibelabschnitt));
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
  public ResponseEntity<GelesenResponseDTO> deleteMapping(@RequestParam UUID id) {
    return ResponseEntity.status(200).body(new GelesenResponseDTO( gelesenService.deleteGelesen(id), "Deleted entry with id " + id));
  }
}