package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CreatedFertilizer;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {
  private FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create fertilizer response entity.
   *
   * @param fertilizer the fertilizer
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<CreatedFertilizer> createFertilizer(@RequestBody Fertilizer fertilizer) {
    CreatedFertilizer newFertilizer = fertilizerService.setFertilizer(fertilizer);
    return ResponseEntity.status(HttpStatus.CREATED).body(newFertilizer);
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  @GetMapping
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<List<CreatedFertilizer>> getAllFertilizers() {
    return ResponseEntity.status(HttpStatus.OK).body(fertilizerService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CreatedFertilizer> getFertilizerById(@PathVariable Long id)
      throws FertilizerNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(fertilizerService.getById(id));
  }
}
