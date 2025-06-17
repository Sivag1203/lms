package com.springboot.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lms.model.Video;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Integer>{
    List<Video> findByModuleIdOrderBySequenceAsc(int moduleId);
}