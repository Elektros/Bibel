package de.bibel.Controller;

import de.bibel.application.model.Gelesen;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GelesenResponseDTO {
  List<Gelesen> result;
  String returnMessage;
}
