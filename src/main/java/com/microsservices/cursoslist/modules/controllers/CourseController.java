package com.microsservices.cursoslist.modules.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.microsservices.cursoslist.modules.entities.CourseEntity;
import com.microsservices.cursoslist.modules.services.CreateCourseUseCase;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/cursos")
public class CourseController {

  @Autowired
  CreateCourseUseCase createCourseUseCase;

  // Criar novo curso
  @PostMapping("/")
  public ResponseEntity<Object> postMethodName(@Valid @RequestBody CourseEntity courseEntity) {
    try {
      var result = createCourseUseCase.execute(courseEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

}
