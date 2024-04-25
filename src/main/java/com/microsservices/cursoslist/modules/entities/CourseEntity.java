package com.microsservices.cursoslist.modules.entities;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "course")
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

  public CourseEntity(Optional<CourseEntity> course) {

  }

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank(message = "O campo [nome] é obrigatório.")
  private String name;

  @NotBlank(message = "O campo [categoria] é obrigatório.")
  private String category;

  private Boolean active;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
