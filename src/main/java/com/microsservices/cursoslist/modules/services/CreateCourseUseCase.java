package com.microsservices.cursoslist.modules.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsservices.cursoslist.modules.entities.CourseEntity;
import com.microsservices.cursoslist.modules.repositories.CourseRepository;

@Service
public class CreateCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public CourseEntity execute(CourseEntity courseEntity) {

    return this.courseRepository.save(courseEntity);
  }

}
