package com.springboot.lms.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModuleDTO {
    private String moduleTitle;
    private int sequence;
    private List<VideoDTO> videos;

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public List<VideoDTO> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoDTO> videos) {
        this.videos = videos;
    }
}