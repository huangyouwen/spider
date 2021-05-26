package com.easystar.dao;

import com.easystar.entity.Video;

import java.util.List;

public interface VideoDao {
    void insertVideo(Video image);
    List<Video> query(int limit);

    Video getVideoBySrc(String src);
}
