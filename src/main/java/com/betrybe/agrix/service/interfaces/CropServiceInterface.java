package com.betrybe.agrix.service.interfaces;

import com.betrybe.agrix.controller.dto.CreatedCrop;
import com.betrybe.agrix.controller.dto.CreatedFertilizer;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 * The interface Crop service interface.
 */
public interface CropServiceInterface {

  /**
   * Set crop crop.
   *
   * @param crop the crop
   * @param id   the id
   * @return the crop
   * @throws FarmNotFoundException the farm not found exception
   */
  public CreatedCrop setCrop(Crop crop, Long id) throws FarmNotFoundException;

  /**
   * Gets all crops farm id.
   *
   * @param id the id
   * @return the all crops farm id
   * @throws FarmNotFoundException the farm not found exception
   */
  public List<CreatedCrop> getAllCropsFarmId(Long id) throws FarmNotFoundException;

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  public List<CreatedCrop> getAllCrops();

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  public CreatedCrop getCropById(Long id) throws CropNotFoundException;

  /**
   * Gets crops by date.
   *
   * @param start the start
   * @param end   the end
   * @return the crops by date
   */
  public List<CreatedCrop> getCropsByDate(LocalDate start, LocalDate end);

  /**
   * Sets crop in fertilizer.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the crop in fertilizer
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public String setCropInFertilizer(Long cropId, Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException;

  /**
   * Gets all fertilizers in crop.
   *
   * @param id the id
   * @return the all fertilizers in crop
   * @throws CropNotFoundException the crop not found exception
   */
  public List<CreatedFertilizer> getAllFertilizersInCrop(Long id) throws CropNotFoundException;
}
