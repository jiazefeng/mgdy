package com.mgdy.vesta.domain.video.repository;

import com.mgdy.vesta.domain.video.model.VideoEntity;
import com.mgdy.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by Jiazefeng on 2018/6/1.
 */
public interface VideoRepository {
    void add(VideoEntity videoEntity);

    List<VideoEntity> getVideos();

    List<VideoEntity> getVideosByUserId(String userId);

    List<VideoEntity> getVideosByParam(WebPage webPage);

    VideoEntity getVideosByVideoId(String videoId);

    void update(VideoEntity videoEntity);
}
