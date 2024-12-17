package com.davi.agrix.controller.dto;

import com.davi.agrix.entity.Crop;
import java.time.LocalDate;

/**
 * The type Creat crop.
 */
public record CreatCrop(
    String name, Double plantedArea, LocalDate plantedDate, LocalDate harvestDate
) {

  /**
   * To entity crop.
   *
   * @return the crop
   */
  public Crop toEntity() {
    return new Crop(name, plantedArea, plantedDate, harvestDate);
  }
}
