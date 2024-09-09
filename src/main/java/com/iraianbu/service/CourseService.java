package com.iraianbu.service;

import com.iraianbu.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final List<Course> courseList = new ArrayList<>();

    // Add a new course
    public void addCourse(Course course){
        courseList.add(course);
    }

    // List all courses
    public List<Course> getAllCourses(){
        return courseList;
    }

    // List a particular course
    public Optional<Course> getCourseById(int id){
        return courseList.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    // Update a particular course
    public boolean updateCourseName(int id, String newName) {
        Optional<Course> courseOptional = courseList.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setName(newName);
            return true;
        } else {
            return false;
        }
    }

    // Delete the course
    public boolean deleteCourseName(int id){
        return courseList.removeIf(course -> course.getId() == id);
    }
}
