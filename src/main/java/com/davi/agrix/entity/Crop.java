package com.davi.agrix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * The type Crop.
 */
@Entity
@Table(name = "crops")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Crop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NonNull
  private String name;
  @NonNull
  private Double plantedArea;
  @NonNull
  private LocalDate plantedDate;
  @NonNull
  private LocalDate harvestDate;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;

  @ManyToMany(mappedBy = "crops")
  private List<Fertilizer> fertilizers = new ArrayList<>();
}
