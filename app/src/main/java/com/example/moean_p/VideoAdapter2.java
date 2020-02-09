package com.example.moean_p;

public class VideoAdapter2 {

    private String name;
    private String videoUrl;

    public VideoAdapter2(){

    }

    public VideoAdapter2(String name, String videoUrl) {
        if(name.trim().equals("")){
            name="no name";
        }
        this.name = name;
        this.videoUrl = videoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
