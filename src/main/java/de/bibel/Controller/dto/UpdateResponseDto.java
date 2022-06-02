package de.bibel.Controller.dto;

import de.bibel.application.model.Gelesen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateResponseDto {
    Gelesen result;
    String returnMessage;
}
