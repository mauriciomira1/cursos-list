package com.microsservices.cursoslist.modules.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microsservices.cursoslist.modules.entities.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

}
