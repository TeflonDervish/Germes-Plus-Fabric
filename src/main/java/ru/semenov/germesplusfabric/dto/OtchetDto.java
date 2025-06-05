package ru.semenov.germesplusfabric.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtchetDto {
    private LocalDate startDate;
    private LocalDate endDate;

    private String name;
    private String description;
}
