package com.mgdy.vesta.application.video.impl;

import com.mgdy.vesta.application.video.DTO.VideoDTO;
import com.mgdy.vesta.application.video.DTO.VideoReturnDTO;
import com.mgdy.vesta.application.video.inf.VideoService;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.common.restHTTPResult.ErrorApiResult;
import com.mgdy.vesta.common.restHTTPResult.SuccessApiResult;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.domain.video.model.VideoEntity;
import com.mgdy.vesta.domain.video.repository.VideoRepository;
import com.mgdy.vesta.taglib.page.WebPage;
import com.mgdy.vesta.utility.DateUtils;
import com.mgdy.vesta.utility.IdGen;
import com.mgdy.vesta.utility.ImgUpdate.FileUpload;
import com.mgdy.vesta.utility.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Jiazefeng on 2018/6/1.
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public ApiResult uploadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response, String IMAGE_SERVER_URL) {
        //图片上传
        String filePath = null;
        if (!file.isEmpty()) {
//            filePath = FileUpload.uploadFile(request, file);
            filePath = UploadFile.imgUpload(file, IMAGE_SERVER_URL);
        }
        System.out.println(filePath);
        if(filePath.isEmpty()){
            return new ErrorApiResult(404,"文件为空");
        }
        return new SuccessApiResult(filePath);
    }

    @Override
    public ApiResult upload(VideoDTO videoDTO) {
        if (videoDTO != null) {
            VideoEntity videoEntity = new VideoEntity();
            videoEntity.setvId(IdGen.uuid());
            videoEntity.setvUrl(videoDTO.getVideoPath());
            videoEntity.setvContent(videoDTO.getContent());
            videoEntity.setCreateDate(new Date());

            videoEntity.setAvatarUrl(videoDTO.getAvatarUrl());
            videoEntity.setCreateName(videoDTO.getNickName());
            videoEntity.setUserId(videoDTO.getUserId());
            videoRepository.add(videoEntity);

            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("videoInfo", videoEntity);
            return new SuccessApiResult(modelMap);
        }
        return null;
    }

    @Override
    public ApiResult getVideos() {
        List<VideoEntity> videoEntityList = videoRepository.getVideos();
        List<VideoReturnDTO> videoReturnDTOS = new ArrayList<>();
        if (videoEntityList.size() > 0 && videoEntityList != null) {
            for (VideoEntity videoEntity : videoEntityList) {
                VideoReturnDTO videoReturnDTO = new VideoReturnDTO();
                videoReturnDTO.setId(videoEntity.getvId());
                videoReturnDTO.setProfile_image(videoEntity.getAvatarUrl());
                videoReturnDTO.setName(videoEntity.getCreateName());
                videoReturnDTO.setText(videoEntity.getvContent());
                videoReturnDTO.setCreate_time(DateUtils.format(videoEntity.getCreateDate(), DateUtils.FORMAT_LONG));
                videoReturnDTO.setVideo_uri(videoEntity.getvUrl());

                videoReturnDTOS.add(videoReturnDTO);
            }

        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("videos", videoReturnDTOS);
        return new SuccessApiResult(modelMap);
    }

    @Override
    public ApiResult getVideosByUserId(String userId) {
        List<VideoEntity> videoEntityList = videoRepository.getVideosByUserId(userId);
        List<VideoReturnDTO> videoReturnDTOS = new ArrayList<>();
        videoEntityList.forEach(videoEntity -> {
            VideoReturnDTO videoReturnDTO = new VideoReturnDTO();
            videoReturnDTO.setId(videoEntity.getvId());
            videoReturnDTO.setProfile_image(videoEntity.getAvatarUrl());
            videoReturnDTO.setName(videoEntity.getCreateName());
            videoReturnDTO.setText(videoEntity.getvContent());
            videoReturnDTO.setCreate_time(DateUtils.format(videoEntity.getCreateDate(), DateUtils.FORMAT_LONG));
            videoReturnDTO.setVideo_uri(videoEntity.getvUrl());

            videoReturnDTOS.add(videoReturnDTO);
        });
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("videos", videoReturnDTOS);
        return new SuccessApiResult(modelMap);
    }

    @Override
    public List<VideoReturnDTO> getVideoList(WebPage webPage, UserPropertyStaffEntity userPropertystaff) {
        List<VideoEntity> videoEntities = videoRepository.getVideosByParam(webPage);
        List<VideoReturnDTO> videoReturnDTOS = new ArrayList<>();
        videoEntities.forEach(videoEntity -> {
            VideoReturnDTO videoReturnDTO = new VideoReturnDTO();
            videoReturnDTO.setId(videoEntity.getvId());
            videoReturnDTO.setProfile_image(videoEntity.getAvatarUrl());
            videoReturnDTO.setName(videoEntity.getCreateName());
            videoReturnDTO.setText(videoEntity.getvContent());
            videoReturnDTO.setCreate_time(DateUtils.format(videoEntity.getCreateDate(), DateUtils.FORMAT_LONG));
            videoReturnDTO.setVideo_uri(videoEntity.getvUrl());
            videoReturnDTO.setStatus(videoEntity.getStatus());

            videoReturnDTOS.add(videoReturnDTO);
        });
        return videoReturnDTOS;
    }

    @Override
    public boolean toReleaseOrCancel(String videoId, String status, UserPropertyStaffEntity userPropertystaffEntity) {
        VideoEntity videoEntity = videoRepository.getVideosByVideoId(videoId);
        if (videoEntity != null) {
            videoEntity.setStatus(Integer.parseInt(status));

            videoRepository.update(videoEntity);
            return true;
        }
        return false;
    }
}
