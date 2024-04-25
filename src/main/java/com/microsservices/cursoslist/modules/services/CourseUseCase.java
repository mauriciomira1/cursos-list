package com.microsservices.cursoslist.modules.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsservices.cursoslist.modules.entities.CourseEntity;
import com.microsservices.cursoslist.modules.repositories.CourseRepository;

@Service
public class CourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public CourseEntity execute(CourseEntity courseEntity) {

    return this.courseRepository.save(courseEntity);
  }

  public void updateCourse(String id, CourseEntity courseUpdated) {

  }

  public CourseEntity updateStatus(Optional<CourseEntity> existsCourse, Map<String, Boolean> statusMap) {
    CourseEntity course = existsCourse.get();
    Boolean newStatus = statusMap.get("active");
    course.setActive(newStatus);

    return course;
  }
}
