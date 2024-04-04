package com.example.ex_onetoone.Service;

import com.example.ex_onetoone.API.ApiException;
import com.example.ex_onetoone.Model.Course;
import com.example.ex_onetoone.Model.Teacher;
import com.example.ex_onetoone.Repository.CourseRepository;
import com.example.ex_onetoone.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository ;
    private final TeacherRepository teacherRepository ;


    public List<Course> getAll (){
        return courseRepository.findAll();
    }


    public void add(Course course){

        courseRepository.save(course);
    }



    public void update(Integer Id , Course course){

        Course c = courseRepository.findCourseById(Id);

        if (c == null){
            throw new ApiException("Invalid Id");
        }

        c.setName(course.getName());


        courseRepository.save(c);

    }



    public void delete(Integer Id){

        Course c = courseRepository.findCourseById(Id);

        if (c == null){
            throw new ApiException("Invalid Id");
        }


        courseRepository.delete(c);

    }




    public void assignCourseToTeacher(Integer CourseId , Integer TeacherId){
        Course c = courseRepository.findCourseById(CourseId);
        Teacher t = teacherRepository.findTeacherById(TeacherId);

        if (c == null || t == null){
            throw new ApiException("cannot assign");
        }

        c.setTeacher(t);

        courseRepository.save(c);
    }



    //    endpoint


    public String getTeacherByCourseID(Integer CourseId){

        Course c = courseRepository.findCourseById(CourseId);
        Teacher t = teacherRepository.findTeacherById(c.getTeacher().getId());

        if (c == null || t == null){
            throw new ApiException("invalid teacher id or course id");
        }

        return t.getName();

    }


}
