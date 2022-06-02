package de.bibel.Controller.dto;

import de.bibel.application.model.Gelesen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GelesenResponseDTO {
  List<Gelesen> result;
  String returnMessage;
}
