package de.bibel.Controller.dto;

import de.bibel.application.model.Gelesen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateResponseDto {
    Optional<Gelesen> result;
    String returnMessage;
}
