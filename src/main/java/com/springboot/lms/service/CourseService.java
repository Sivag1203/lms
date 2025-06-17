package com.springboot.lms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.springboot.lms.dto.*;
import com.springboot.lms.model.*;
import com.springboot.lms.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	private CourseRepository courseRepository;
	private AuthorRepository authorRepository;
	@Autowired private ModuleRepository moduleRepository;
	@Autowired private VideoRepository videoRepository;
	@Autowired private ReviewRepository reviewRepository;
	Logger logger = LoggerFactory.getLogger("CourseService");

	public CourseService(CourseRepository courseRepository, AuthorRepository authorRepository) {
		this.courseRepository = courseRepository;
		this.authorRepository = authorRepository;
	}

	public Course postCourse(Course course, String username) {
		/** Fetch Author by username */
		Author author = authorRepository.getByUsername(username);
		logger.info("Author record fetched by username");
		/* Attach author to course */
		course.setAuthor(author);
		logger.info("Adding.. Author to Database");
		/** Save the course in DB */
		return courseRepository.save(course);
	}

	public List<Course> getAllCourses(int page, int size) {
		/** Activate Pageable Interface */
		Pageable pageable = PageRequest.of(page, size);
		/** Call findAll inbuilt method as pass this pageable interface ref */
		return courseRepository.findAll(pageable).getContent();
	}

	public List<Course> getCoursesByAuthor(String username) {
		return courseRepository.getCoursesByAuthor(username);

	}

	public CourseDetailsDTO getCourseDetails(int courseId) {
		Course course = courseRepository.findCourseWithAuthorById(courseId)
				.orElseThrow(() -> new RuntimeException("Course not found"));

		CourseDetailsDTO dto = new CourseDetailsDTO();
		dto.setId(course.getId());
		dto.setTitle(course.getTitle());
		dto.setCredits(course.getCredits());
		dto.setImageUrl(course.getImageUrl());

		// Author
		AuthorDTO authorDto = new AuthorDTO();
		authorDto.setName(course.getAuthor().getName());
		authorDto.setContact(course.getAuthor().getContact());
		authorDto.setProfilePic(course.getAuthor().getProfilePic());
		dto.setAuthor(authorDto);

		// Modules & Videos
		List<CModule> modules = moduleRepository.findByCourseIdOrderBySequenceAsc(courseId);
		List<ModuleDTO> moduleDTOs = new ArrayList<>();

		for (CModule m : modules) {
			ModuleDTO mDto = new ModuleDTO();
			mDto.setModuleTitle(m.getModuleTitle());
			mDto.setSequence(m.getSequence());

			List<Video> videos = videoRepository.findByModuleIdOrderBySequenceAsc(m.getId());
			List<VideoDTO> videoDTOs = new ArrayList<>();
			for (Video v : videos) {
				VideoDTO vDto = new VideoDTO();
				vDto.setVideoTitle(v.getVideoTitle());
				vDto.setPlayTime(v.getPlayTime());
				vDto.setVideoCode(v.getVideoCode());
				vDto.setSequence(v.getSequence());
				videoDTOs.add(vDto);
			}
			mDto.setVideos(videoDTOs);
			moduleDTOs.add(mDto);
		}
		dto.setModules(moduleDTOs);

		// Reviews
		List<Review> reviews = reviewRepository.findByCourseId(courseId);
		List<ReviewDTO> reviewDTOs = new ArrayList<>();
		for (Review r : reviews) {
			ReviewDTO rDto = new ReviewDTO();
			rDto.setComment(r.getComment());
			rDto.setRating(r.getRating());
			reviewDTOs.add(rDto);
		}
		dto.setReviews(reviewDTOs);

		return dto;
	}
}