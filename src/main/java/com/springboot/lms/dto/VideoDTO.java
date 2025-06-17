package com.springboot.lms.dto;

import org.springframework.stereotype.Component;

@Component
public class VideoDTO {
    private String videoTitle;
    private float playTime;
    private String videoCode;
    private int sequence;

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public float getPlayTime() {
        return playTime;
    }

    public void setPlayTime(float playTime) {
        this.playTime = playTime;
    }

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}