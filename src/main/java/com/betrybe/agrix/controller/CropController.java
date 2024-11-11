package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CreatedCrop;
import com.betrybe.agrix.controller.dto.CreatedFertilizer;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import com.betrybe.agrix.service.interfaces.CropServiceInterface;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private CropServiceInterface cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  public CropController(CropServiceInterface cropService) {
    this.cropService = cropService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER')")
  public ResponseEntity<List<CreatedCrop>> getAllCrops() {
    return ResponseEntity.status(HttpStatus.OK).body(cropService.getAllCrops());
  }


  /**
   * Gets crop by id.
   *
   * @param start the start
   * @param end   the end
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/search")
  public ResponseEntity<List<CreatedCrop>> getCropsByDate(
      @RequestParam LocalDate start, @RequestParam LocalDate end
  ) {
    return ResponseEntity.status(HttpStatus.OK).body(cropService.getCropsByDate(start, end));
  }

  /**
   * Gets crop fertilizers.
   *
   * @param cropId the crop id
   * @return the crop fertilizers
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<CreatedFertilizer>> getCropFertilizers(@PathVariable Long cropId)
      throws CropNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(cropService.getAllFertilizersInCrop(cropId));
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}")
  public ResponseEntity<CreatedCrop> getCropById(@PathVariable Long id)
      throws CropNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(cropService.getCropById(id));
  }

  /**
   * Sets crop in fertilizer.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the crop in fertilizer
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> setCropInFertilizer(
      @PathVariable Long cropId, @PathVariable Long fertilizerId
  )
      throws CropNotFoundException, FertilizerNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(cropService.setCropInFertilizer(cropId, fertilizerId));
  }
}
