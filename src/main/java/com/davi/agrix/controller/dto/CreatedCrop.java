package com.davi.agrix.controller.dto;

import java.time.LocalDate;

/**
 * The type Created crop.
 */
public record CreatedCrop(
    Long id,
    String name,
    Double plantedArea,
    Long farmId,
    LocalDate plantedDate,
    LocalDate harvestDate) {
}
