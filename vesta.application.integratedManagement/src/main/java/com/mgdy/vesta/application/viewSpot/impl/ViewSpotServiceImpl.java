package com.mgdy.vesta.application.viewSpot.impl;

import com.mgdy.vesta.application.house.DTO.HouseDTO;
import com.mgdy.vesta.application.viewSpot.DTO.ViewSpotDTO;
import com.mgdy.vesta.application.viewSpot.inf.ViewSpotService;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.common.restHTTPResult.SuccessApiResult;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.domain.viewSpot.model.ViewSpotEntity;
import com.mgdy.vesta.domain.viewSpot.repository.ViewSpotRepository;
import com.mgdy.vesta.taglib.page.WebPage;
import com.mgdy.vesta.utility.IdGen;
import com.mgdy.vesta.utility.ImgUpdate.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Jason on 2017/7/11.
 */
@Service
public class ViewSpotServiceImpl implements ViewSpotService {
    @Autowired
    private ViewSpotRepository viewSpotRepository;

    @Override
    public List<ViewSpotDTO> getViewSpotDTOInfoList(ViewSpotDTO viewSpiotDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff) {
        Map map = new HashMap();
        List<ViewSpotEntity> viewSpotEntityList = viewSpotRepository.getViewSpotInfoList(map, webPage);
        List<ViewSpotDTO> viewSpotDTOList = new ArrayList<ViewSpotDTO>();
        if (viewSpotEntityList != null && viewSpotEntityList.size() > 0) {
            for (ViewSpotEntity viewSpotEntity : viewSpotEntityList) {
                ViewSpotDTO viewSpotDTO = new ViewSpotDTO();
                viewSpotDTO.setViewSpotId(viewSpotEntity.getViewSpotId());
                viewSpotDTO.setViewSpotTitle(viewSpotEntity.getViewSpotTitle());
                viewSpotDTO.setViewSpotDescribe(viewSpotEntity.getViewSpotDescribe());
                if (viewSpotEntity.getClassify().equals("1")) {
                    viewSpotDTO.setClassify("风景");
                }
                if (viewSpotEntity.getClassify().equals("2")) {
                    viewSpotDTO.setClassify("美食");
                }
                if (viewSpotEntity.getClassify().equals("3")) {
                    viewSpotDTO.setClassify("娱乐");
                }
                viewSpotDTOList.add(viewSpotDTO);
            }
        }
        return viewSpotDTOList;
    }

    @Override
    public void saveViewSpotInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile viewSpotImgFile, ViewSpotDTO viewSpotDTO, HttpServletRequest req, String imgType) {
        if (viewSpotDTO != null) {
            ViewSpotEntity viewSpotEntity = new ViewSpotEntity();
            viewSpotEntity.setViewSpotId(IdGen.uuid());
            viewSpotEntity.setViewSpotTitle(viewSpotDTO.getViewSpotTitle());
            viewSpotEntity.setViewSpotDescribe(viewSpotDTO.getViewSpotDescribe());
            if (!viewSpotImgFile.isEmpty()) {
                String imgUrl = FileUpload.upload(req, viewSpotImgFile, imgType);
                viewSpotEntity.setViewSpotImageUrl(imgUrl);
            }
            viewSpotEntity.setClassify(viewSpotDTO.getClassify());
            viewSpotEntity.setCreateOn(new Date());
            viewSpotEntity.setCreateBy(userPropertystaffEntity.getStaffName());
            viewSpotEntity.setModifyOn(new Date());
            viewSpotEntity.setModifyBy(userPropertystaffEntity.getStaffName());
            viewSpotRepository.addViewSpot(viewSpotEntity);
        }
    }

    @Override
    public ViewSpotDTO getViewSpotInfoById(String viewSpotId) {
        ViewSpotEntity viewSpotEntity = viewSpotRepository.getViewSpotInfoById(viewSpotId);
        ViewSpotDTO viewSpotDTO = null;
        if (viewSpotEntity != null) {
            viewSpotDTO = new ViewSpotDTO();
            viewSpotDTO.setViewSpotId(viewSpotEntity.getViewSpotId());
            viewSpotDTO.setClassify(viewSpotEntity.getClassify());
            viewSpotDTO.setViewSpotTitle(viewSpotEntity.getViewSpotTitle());
            viewSpotDTO.setViewSpotDescribe(viewSpotEntity.getViewSpotDescribe());
            viewSpotDTO.setViewSpotImageUrl(viewSpotEntity.getViewSpotImageUrl());
        }
        return viewSpotDTO;
    }

    @Override
    public void updateViewSpotInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile viewSpotImgFile, ViewSpotDTO viewSpotDTO, HttpServletRequest req, String imgType) {
        ViewSpotEntity viewSpotEntity = viewSpotRepository.getViewSpotInfoById(viewSpotDTO.getViewSpotId());
        if (viewSpotEntity != null) {
            viewSpotEntity.setClassify(viewSpotDTO.getClassify());
            viewSpotEntity.setViewSpotTitle(viewSpotDTO.getViewSpotTitle());
            viewSpotEntity.setViewSpotDescribe(viewSpotDTO.getViewSpotDescribe());
            if (!viewSpotImgFile.isEmpty()) {
                String imgUrl = FileUpload.upload(req, viewSpotImgFile, imgType);
                viewSpotEntity.setViewSpotImageUrl(imgUrl);
            }
            viewSpotEntity.setModifyBy(userPropertystaffEntity.getStaffName());
            viewSpotEntity.setModifyOn(new Date());
            viewSpotRepository.updateViewSpot(viewSpotEntity);
        }
    }

    @Override
    public void deleteViewSpotInfo(String viewSoptId) {
        ViewSpotEntity viewSpotEntity = viewSpotRepository.getViewSpotInfoById(viewSoptId);
        if (viewSpotEntity != null) {
            viewSpotRepository.deleteViewSpotInfo(viewSpotEntity);
        }
    }

    @Override
    public ApiResult getViewSpotInfo() {
        List<ViewSpotEntity> viewSpotEntityList = viewSpotRepository.getViewSpotInfoList();
        List<ViewSpotDTO> viewSpotDTOList = new ArrayList<ViewSpotDTO>();//景色
        List<ViewSpotDTO> foodDTOList = new ArrayList<ViewSpotDTO>();//美食
        List<ViewSpotDTO> dispDTOList = new ArrayList<ViewSpotDTO>();//娱乐
        if (viewSpotEntityList != null && viewSpotEntityList.size() > 0) {
            for (ViewSpotEntity viewSpotEntity : viewSpotEntityList) {
                ViewSpotDTO viewSpotDTO = new ViewSpotDTO();
                viewSpotDTO.setViewSpotId(viewSpotEntity.getViewSpotId());
                viewSpotDTO.setViewSpotTitle(viewSpotEntity.getViewSpotTitle());
                viewSpotDTO.setViewSpotDescribe(viewSpotEntity.getViewSpotDescribe());
                viewSpotDTO.setViewSpotImageUrl(viewSpotEntity.getViewSpotImageUrl());
                if(viewSpotEntity.getClassify().equals("1")){
                    viewSpotDTOList.add(viewSpotDTO);
                }else if(viewSpotEntity.getClassify().equals("2")){
                    foodDTOList.add(viewSpotDTO);
                }else {
                    dispDTOList.add(viewSpotDTO);
                }
            }
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("viewSpotInfoList", viewSpotDTOList);
        modelMap.addAttribute("foodInfoList", foodDTOList);
        modelMap.addAttribute("dispInfoList", dispDTOList);
        return new SuccessApiResult(modelMap);
    }
}
