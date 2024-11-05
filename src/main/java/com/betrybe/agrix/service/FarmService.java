package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import com.betrybe.agrix.service.interfaces.FarmServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService implements FarmServiceInterface {

  private FarmRepository farmRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   */

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  @Override
  public Farm setFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  @Override
  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  @Override
  public Farm getFarmById(Long id) throws FarmNotFoundException {
    return farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
  }

}
