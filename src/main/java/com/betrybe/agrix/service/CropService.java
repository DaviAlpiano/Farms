package com.betrybe.agrix.service;

import com.betrybe.agrix.controller.dto.CreatedCrop;
import com.betrybe.agrix.controller.dto.CreatedFertilizer;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import com.betrybe.agrix.service.interfaces.CropServiceInterface;
import com.betrybe.agrix.service.interfaces.FarmServiceInterface;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService implements CropServiceInterface {
  private CropRepository cropRepository;
  private FarmServiceInterface farmService;
  private FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository the crop repository
   * @param farmService    the farm service
   */
  @Autowired
  public CropService(
      CropRepository cropRepository,
      FarmServiceInterface farmService,
      FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
    this.fertilizerRepository = fertilizerRepository;
  }

  @Override
  public CreatedCrop setCrop(Crop crop, Long id) throws FarmNotFoundException {
    Farm farm = farmService.getFarmById(id);
    crop.setFarm(farm);
    Crop newCrop = cropRepository.save(crop);
    return new CreatedCrop(
        newCrop.getId(),
        newCrop.getName(),
        newCrop.getPlantedArea(),
        newCrop.getFarm().getId(),
        newCrop.getPlantedDate(),
        newCrop.getHarvestDate());
  }

  @Override
  public List<CreatedCrop> getAllCropsFarmId(Long id) throws FarmNotFoundException {
    farmService.getFarmById(id);
    List<Crop> crops = cropRepository
        .findAll().stream()
          .filter(crop -> id.equals(crop.getFarm().getId())).toList();
    return crops.stream().map(crop -> new CreatedCrop(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate())).toList();
  }

  @Override
  public List<CreatedCrop> getAllCrops() {
    return cropRepository.findAll().stream().map(crop -> new CreatedCrop(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate())).toList();
  }

  @Override
  public CreatedCrop getCropById(Long id) throws CropNotFoundException {
    Crop crop = cropRepository.findById(id).orElseThrow(CropNotFoundException::new);
    return new CreatedCrop(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate());
  }

  @Override
  public List<CreatedCrop> getCropsByDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end)
        .stream().map(crop -> new CreatedCrop(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate())).toList();
  }

  @Override
  public String setCropInFertilizer(Long cropId, Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = cropRepository.findById(cropId).orElseThrow(CropNotFoundException::new);
    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(FertilizerNotFoundException::new);
    fertilizer.getCrops().add(crop);
    fertilizerRepository.save(fertilizer);
    return "Fertilizante e plantação associados com sucesso!";
  }

  @Override
  public List<CreatedFertilizer> getAllFertilizersInCrop(Long cropId) throws CropNotFoundException {
    return cropRepository.findById(cropId)
        .orElseThrow(CropNotFoundException::new)
        .getFertilizers().stream().map(fertilizer ->
        new CreatedFertilizer(
            fertilizer.getId(),
            fertilizer.getName(),
            fertilizer.getBrand(),
            fertilizer.getComposition()
        )).toList();
  }
}
