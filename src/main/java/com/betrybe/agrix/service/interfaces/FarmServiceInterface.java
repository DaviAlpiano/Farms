package com.betrybe.agrix.service.interfaces;

import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;

/**
 * The interface Farm service interface.
 */
public interface FarmServiceInterface {

  /**
   * Sets farm.
   *
   * @param farm the farm
   * @return the farm repository
   */
  public Farm setFarm(Farm farm);

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  public List<Farm> getAllFarms();

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFoundException the farm not found exception
   */
  public Farm getFarmById(Long id) throws FarmNotFoundException;
}
