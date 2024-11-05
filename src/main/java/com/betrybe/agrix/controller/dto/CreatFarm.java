package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * The type Created farm.
 */
public record CreatFarm(String name, Double size) {

  /**
   * To entity farm.
   *
   * @return the farm
   */
  public Farm toEntity() {
    return new Farm(name, size);
  }
}
