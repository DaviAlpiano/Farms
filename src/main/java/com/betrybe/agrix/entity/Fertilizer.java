package com.betrybe.agrix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * The type Fertilizer.
 */
@Entity
@Table(name = "fertilizers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Fertilizer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private String name;

  @NonNull
  private String brand;

  @NonNull
  private String composition;

  @ManyToMany
  @JoinTable(
      name = "crop_fertilizer",
      joinColumns = @JoinColumn(name = "fertilizer_id"),
      inverseJoinColumns = @JoinColumn(name = "crop_id")
  )
  private List<Crop> crops = new ArrayList<>();
}
