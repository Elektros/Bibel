package de.bibel.Controller;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GelesenRequestDTO {
  String bibelabschnitt;
  ArrayList<String> lieblingsverse;
  ArrayList<String> versText;
  ArrayList<String> labels;
  String kommentar;
  String leser;
}
