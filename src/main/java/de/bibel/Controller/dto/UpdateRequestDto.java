package de.bibel.Controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class UpdateRequestDto {
    UUID id;
    String bibelabschnitt;
    ArrayList<String> lieblingsverse;
    ArrayList<String> versText;
    ArrayList<String> labels;
    String kommentar;
    String leser;
}
