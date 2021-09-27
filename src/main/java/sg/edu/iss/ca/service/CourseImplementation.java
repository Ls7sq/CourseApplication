package sg.edu.iss.ca.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.repo.CourseRepository;
import sg.edu.iss.ca.repo.GradeRepository;
import sg.edu.iss.ca.repo.StudentRepository;

@Service
public class CourseImplementation implements CourseInterface {	
	@Autowired
	StudentRepository srepo;
	
	@Autowired
	CourseRepository crepo;
	
	@Autowired
	GradeRepository grepo;


	@Transactional
	public Course findCourseById(Integer id) {
		return crepo.findById(id).get();
	}

    @Transactional
    public List<Course> findAllCourses() {
        return crepo.findAll();
    }

    @Transactional
    public void createCourseRecord(Course course) {
        crepo.save(course);
    }


    @Transactional
    public void deleteCourse(Integer courseId) {
        Course courseToDelete = crepo.findCourseById(courseId);
        crepo.delete(courseToDelete);
    }
    
	@Transactional
	public boolean checkExist(Course course) {
		if (crepo.findAll().contains(course)) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public void save(Course course) {
		crepo.save(course);
		
	}

	@Override
	public Page<Course> findPaginated(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.crepo.findAll(pageable);
	}


}
