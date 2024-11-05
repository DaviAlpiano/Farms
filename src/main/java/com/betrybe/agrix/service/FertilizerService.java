package com.betrybe.agrix.service;

import com.betrybe.agrix.controller.dto.CreatedFertilizer;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import com.betrybe.agrix.service.interfaces.FertilizerServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Fertilizer service.
 */
@Service
public class FertilizerService implements FertilizerServiceInterface {
  private FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Fertilizer service.
   *
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  @Override
  public CreatedFertilizer setFertilizer(Fertilizer fertilizer) {
    Fertilizer newFertilizer = fertilizerRepository.save(fertilizer);
    return new CreatedFertilizer(
        newFertilizer.getId(),
        newFertilizer.getName(),
        newFertilizer.getBrand(),
        newFertilizer.getComposition()
        );
  }

  @Override
  public List<CreatedFertilizer> getAll() {
    return fertilizerRepository.findAll().stream().map(fertilizer ->
        new CreatedFertilizer(
            fertilizer.getId(),
            fertilizer.getName(),
            fertilizer.getBrand(),
            fertilizer.getComposition()
        )).toList();
  }

  @Override
  public CreatedFertilizer getById(Long id) throws FertilizerNotFoundException {
    Fertilizer fertilizer = fertilizerRepository.findById(id)
        .orElseThrow(FertilizerNotFoundException::new);
    return new CreatedFertilizer(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }
}
