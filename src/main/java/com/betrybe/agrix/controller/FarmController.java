package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CreatCrop;
import com.betrybe.agrix.controller.dto.CreatFarm;
import com.betrybe.agrix.controller.dto.CreatedCrop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import com.betrybe.agrix.service.interfaces.CropServiceInterface;
import com.betrybe.agrix.service.interfaces.FarmServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private FarmServiceInterface farmService;
  private CropServiceInterface cropService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   * @param cropService the crop service
   */
  @Autowired
  public FarmController(FarmServiceInterface farmService, CropServiceInterface cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Create farm response entity.
   *
   * @param farm the farm
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Farm> createFarm(@RequestBody CreatFarm farm) {
    Farm newFarm = farmService.setFarm(farm.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  public ResponseEntity<List<Farm>> getAllFarms() {
    return ResponseEntity.status(HttpStatus.OK).body(farmService.getAllFarms());
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long id)
      throws FarmNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(farmService.getFarmById(id));
  }

  /**
   * Create crop response entity.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the response entity
   * @throws FarmNotFoundException the farm not found exception
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CreatedCrop> createCrop(
      @PathVariable Long farmId, @RequestBody CreatCrop crop
  )
      throws FarmNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(cropService.setCrop(crop.toEntity(), farmId));
  }

  /**
   * Gets crops.
   *
   * @param farmId the farm id
   * @return the crops
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CreatedCrop>> getCrops(@PathVariable Long farmId)
      throws FarmNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(cropService.getAllCropsFarmId(farmId));
  }
}