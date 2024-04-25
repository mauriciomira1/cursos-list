package com.microsservices.cursoslist.modules.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.microsservices.cursoslist.modules.entities.CourseEntity;
import com.microsservices.cursoslist.modules.repositories.CourseRepository;
import com.microsservices.cursoslist.modules.services.CourseUseCase;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/cursos")
public class CourseController {

  @Autowired
  CourseUseCase courseUseCase;

  @Autowired
  CourseRepository courseRepository;

  // Criar novo curso
  @PostMapping("/")
  public ResponseEntity<Object> newCourse(@Valid @RequestBody CourseEntity courseEntity) {
    try {
      var result = courseUseCase.execute(courseEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // Listar todos os cursos
  @GetMapping("/")
  public List<CourseEntity> listAllCourses() {
    return courseRepository.findAll();
  }

  // Listar curso pelo nome
  @GetMapping("/{name}")
  public ResponseEntity<Object> getCourseByName(@PathVariable String name) {
    try {
      Optional<CourseEntity> course = this.courseRepository.findByName(name);
      return ResponseEntity.ok().body(course);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // Atualizar um curso
  @PutMapping("/{id}")
  public ResponseEntity<CourseEntity> updateCourse(@PathVariable String id,
      @RequestBody CourseEntity newCourse) {

    var course = this.courseRepository.findById(UUID.fromString(id.toString()));

    if (course.isEmpty())
      return ResponseEntity.notFound().build();

    CourseEntity courseUpdated = course.get();

    if (newCourse.getName() != null)
      courseUpdated.setName(newCourse.getName());

    if (newCourse.getCategory() != null)
      courseUpdated.setCategory(newCourse.getCategory());

    courseRepository.save(courseUpdated);
    return ResponseEntity.ok().body(courseUpdated);
  }

  // Remover um curso
  @DeleteMapping("/{id}")
  public ResponseEntity<String> removeCourse(@PathVariable String id) {
    Optional<CourseEntity> course = courseRepository.findById(UUID.fromString(id.toString()));

    try {
      if (course.isEmpty())
        return ResponseEntity.notFound().build();

      courseRepository.deleteById(UUID.fromString(id.toString()));

      return ResponseEntity.ok().body("Curso removido com sucesso!");
    } catch (DataAccessException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  // Atualizar status "active" do curso
  @PatchMapping("/{id}/active")
  public ResponseEntity<String> updateStatusCourse(@PathVariable String id,
      @RequestBody Map<String, Boolean> statusMap) {
    Optional<CourseEntity> existsCourse = courseRepository.findById(UUID.fromString(id.toString()));

    if (existsCourse.isEmpty())
      return ResponseEntity.notFound().build();

    CourseEntity course = courseUseCase.updateStatus(existsCourse, statusMap);

    courseRepository.save(course);

    return ResponseEntity.ok("Status do curso atualizado com sucessso!");
  }

}
