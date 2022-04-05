package de.bibel.Controller;

import de.bibel.application.Service.GelesenService;
import de.bibel.application.model.Gelesen;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GelesenController {

  private final GelesenService gelesenService;

  @PostMapping("/gelesen")
  public void saveGelesen(
      @RequestParam String bibelabschnitt,
      @RequestParam List<String> lieblingsverse,
      @RequestParam List<String> versText,
      @RequestParam List<String> labels,
      @RequestParam String kommentar,
      @RequestParam String leser) {
    gelesenService.saveGelesen(bibelabschnitt, lieblingsverse, versText, labels, leser, kommentar);
  }

  @GetMapping("/gelesen")
  public List<Gelesen> getGelesen() {
    return gelesenService.getGelesen();
  }
}