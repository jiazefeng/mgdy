package com.mgdy.vesta.application.video.inf;

import com.mgdy.vesta.application.video.DTO.VideoDTO;
import com.mgdy.vesta.application.video.DTO.VideoReturnDTO;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Jiazefeng on 2018/6/1.
 */
public interface VideoService {
    ApiResult uploadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response, String IMAGE_SERVER_URL);

    ApiResult upload(VideoDTO videoDTO);

    ApiResult getVideos();

    ApiResult getVideosByUserId(String userId);

    List<VideoReturnDTO> getVideoList(WebPage webPage, UserPropertyStaffEntity userPropertystaff);

    boolean toReleaseOrCancel(String videoId, String status, UserPropertyStaffEntity userPropertystaffEntity);
}
