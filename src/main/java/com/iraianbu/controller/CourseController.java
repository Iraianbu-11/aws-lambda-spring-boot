package com.iraianbu.controller;

import com.iraianbu.model.Course;
import com.iraianbu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(produces = "application/json" , consumes = "application/json")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        courseService.addCourse(course);
        return new ResponseEntity<>(course , HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courseList = courseService.getAllCourses();
        return new ResponseEntity<>(courseList , HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Course> getCourseById(@PathVariable int id){
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(value -> new ResponseEntity<>(value , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(value = "/{id}" , consumes = "application/json")
    public ResponseEntity<Void> updateCourseName(@PathVariable int id , @RequestBody Course courseName){
        String name = courseName.getName();
        boolean isCourseNameUpdated = courseService.updateCourseName(id,name);
        if(isCourseNameUpdated) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}" , produces = "application/json")
    public ResponseEntity<Void> deleteCourseName(@PathVariable int id){
        boolean isCourseDeleted = courseService.deleteCourseName(id);
        if(isCourseDeleted) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
