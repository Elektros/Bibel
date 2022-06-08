package de.bibel.Controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class UpdateRequestDto {
    String id;
    String bibelabschnitt;
    ArrayList<String> lieblingsverse;
    ArrayList<String> versText;
    ArrayList<String> labels;
    String kommentar;
    String leser;
}
