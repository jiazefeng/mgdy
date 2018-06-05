package com.mgdy.vesta.application.video.inf;

import com.mgdy.vesta.application.video.DTO.VideoDTO;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jiazefeng on 2018/6/1.
 */
public interface VideoService {
    ApiResult uploadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response, String IMAGE_SERVER_URL);

    ApiResult upload(VideoDTO videoDTO);

    ApiResult getVideos();
}
