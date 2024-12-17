package com.davi.agrix.controller.dto;

import com.davi.agrix.entity.Farm;

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
