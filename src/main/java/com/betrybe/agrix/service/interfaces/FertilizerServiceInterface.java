package com.betrybe.agrix.service.interfaces;

import com.betrybe.agrix.controller.dto.CreatedFertilizer;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.util.List;

/**
 * The interface Fertilizer service interface.
 */
public interface FertilizerServiceInterface {

  /**
   * Sets fertilizer.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer
   */
  public CreatedFertilizer setFertilizer(Fertilizer fertilizer);

  /**
   * Gets all.
   *
   * @return the all
   */
  public List<CreatedFertilizer> getAll();

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  public CreatedFertilizer getById(Long id) throws FertilizerNotFoundException;
}
