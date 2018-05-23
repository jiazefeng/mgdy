package com.mgdy.vesta.application.house.impl;

import com.mgdy.vesta.application.house.DTO.HouseDTO;
import com.mgdy.vesta.application.house.DTO.HouseListDTO;
import com.mgdy.vesta.application.house.DTO.HouseWebDTO;
import com.mgdy.vesta.application.house.inf.HouseService;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.common.restHTTPResult.SuccessApiResult;
import com.mgdy.vesta.domain.house.model.HouseEntity;
import com.mgdy.vesta.domain.house.model.HouseImageEntity;
import com.mgdy.vesta.domain.house.repository.HouseRepository;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import com.mgdy.vesta.utility.IdGen;
import com.mgdy.vesta.utility.ImgUpdate.FileUpload;
import freemarker.ext.beans.MapModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Jason on 2017/7/10.
 */
@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseRepository houseRepository;

    @Override
    public List<HouseDTO> getHouseInfoList(HouseDTO houseDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff) {
        Map map = new HashMap();
        List<HouseEntity> houseEntityList = houseRepository.getHouseInfoList(map, webPage);
        List<HouseDTO> houseDTOList = new ArrayList<HouseDTO>();
        if (houseEntityList != null && houseEntityList.size() > 0) {
            for (HouseEntity houseEntity : houseEntityList) {
                HouseDTO houseDTO1 = new HouseDTO();
                houseDTO1.setHouseId(houseEntity.getHouseId());
                houseDTO1.setHouseType(houseEntity.getHouseType());
                houseDTO1.setHousePrice(houseEntity.getHousePrice());
                houseDTO1.setGroupBuyingPrice(houseEntity.getGroupBuyingPrice());
                houseDTO1.setHouseDescribe(houseEntity.getHouseDescribe());
                houseDTOList.add(houseDTO1);
            }
        }
        return houseDTOList;
    }

    @Override
    public void saveHouseInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile homePageimgFile, MultipartFile[] houseDetailImages, HouseDTO houseDTO, HttpServletRequest req, String imgType) {
        if (houseDTO != null) {
            HouseEntity houseEntity = new HouseEntity();
            houseEntity.setHouseId(IdGen.uuid());
            houseEntity.setHouseType(houseDTO.getHouseType());
            houseEntity.setHousePrice(houseDTO.getHousePrice());
            houseEntity.setGroupBuyingPrice(houseDTO.getGroupBuyingPrice());
            houseEntity.setHouseDescribe(houseDTO.getHouseDescribe());
            houseEntity.setHouseState("1");
            houseEntity.setCreateOn(new Date());
            houseEntity.setCreateBy(userPropertystaffEntity.getCreateBy());
            houseEntity.setModifyBy(userPropertystaffEntity.getModifyBy());
            houseEntity.setModifyOn(new Date());
            if (!homePageimgFile.isEmpty()) {
                String houseImgUrl = FileUpload.upload(req, homePageimgFile, imgType);
                houseEntity.setHouseImge(houseImgUrl);
            }
            boolean f = houseRepository.addHouse(houseEntity);
            if (f) {
                if (houseDetailImages != null) {
                    for (MultipartFile houseFile : houseDetailImages) {
                        String houseImgUrl = FileUpload.upload(req, houseFile, imgType);
                        HouseImageEntity houseImageEntity = new HouseImageEntity();
                        houseImageEntity.setImageId(IdGen.uuid());
                        houseImageEntity.setHouseId(houseEntity.getHouseId());
                        houseImageEntity.setImgUrl(houseImgUrl);
                        houseImageEntity.setCreateOn(new Date());

                        houseRepository.addHouseImage(houseImageEntity);
                    }
                }
            }
        }
    }

    @Override
    public HouseDTO getHouseInfoById(String houseId) {
        HouseEntity houseEntity = houseRepository.getHouseInfoById(houseId);
        List<HouseImageEntity> houseImageEntities = houseRepository.getHouseImageList();
        HouseDTO houseDTO = null;
        if (houseEntity != null) {
            houseDTO = new HouseDTO();
            houseDTO.setHouseId(houseEntity.getHouseId());
            houseDTO.setHouseType(houseEntity.getHouseType());
            houseDTO.setHousePrice(houseEntity.getHousePrice());
            houseDTO.setGroupBuyingPrice(houseEntity.getGroupBuyingPrice());
            houseDTO.setHouseDescribe(houseEntity.getHouseDescribe());
            houseDTO.setHouseImge(houseEntity.getHouseImge());
            if (houseImageEntities != null && houseImageEntities.size() > 0) {
                List<String> list = new ArrayList<String>();
                for (HouseImageEntity houseImageEntity : houseImageEntities) {
                    if (houseImageEntity.getHouseId().equals(houseEntity.getHouseId())) {
                        String imageUrl = houseImageEntity.getImgUrl();
                        list.add(imageUrl);
                    }
                }
                houseDTO.setHouseImageList(list);
            }
        }
        return houseDTO;
    }

    @Override
    public void updateHouseInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile homePageimgFile, MultipartFile[] houseDetailImages, HouseDTO houseDTO, HttpServletRequest req, String imgType) {
        HouseEntity houseEntity = houseRepository.getHouseInfoById(houseDTO.getHouseId());
        if (houseEntity != null) {
            houseEntity.setHouseType(houseDTO.getHouseType());
            houseEntity.setHousePrice(houseDTO.getHousePrice());
            houseEntity.setGroupBuyingPrice(houseDTO.getGroupBuyingPrice());
            houseEntity.setHouseDescribe(houseDTO.getHouseDescribe());
            houseEntity.setModifyBy(userPropertystaffEntity.getModifyBy());
            houseEntity.setModifyOn(new Date());
            if (!homePageimgFile.isEmpty()) {
                String houseImgUrl = FileUpload.upload(req, homePageimgFile, imgType);
                houseEntity.setHouseImge(houseImgUrl);
            }
            boolean f = houseRepository.updateHouse(houseEntity);
            if (f) {
                if (houseDetailImages != null && !houseDetailImages[0].isEmpty()) {
                    houseRepository.deleteHouseImageInfoById(houseEntity.getHouseId());
                    for (MultipartFile houseFile : houseDetailImages) {
                        String houseImgUrl = FileUpload.upload(req, houseFile, imgType);
                        HouseImageEntity houseImageEntity = new HouseImageEntity();
                        houseImageEntity.setImageId(IdGen.uuid());
                        houseImageEntity.setHouseId(houseEntity.getHouseId());
                        houseImageEntity.setImgUrl(houseImgUrl);
                        houseImageEntity.setCreateOn(new Date());

                        houseRepository.addHouseImage(houseImageEntity);
                    }
                }
            }
        }
    }

    @Override
    public void deleteHouseInfo(String houseId) {
        HouseEntity houseEntity = houseRepository.getHouseInfoById(houseId);
        if (houseEntity != null) {
            houseRepository.deleteHouseInfo(houseEntity);
            houseRepository.deleteHouseImageInfoById(houseEntity.getHouseId());
        }
    }

    @Override
    public ApiResult getHouseInfoList() {
        List<HouseEntity> houseEntityList = houseRepository.getHouseInfoList("");
//        List<HouseEntity> houseEntityList = houseRepository.getHouseInfoList("ECONOMICS");//经济房
//        List<HouseEntity> houseEntityList1 = houseRepository.getHouseInfoList("COMFORTABLE");//舒适房
//        List<HouseEntity> houseEntityList2 = houseRepository.getHouseInfoList("HIGH_GRADE");//高档房
//        List<HouseEntity> houseEntityList3 = houseRepository.getHouseInfoList("LUCURY");//豪华房
        List<HouseListDTO> houseListDTOS = new ArrayList<HouseListDTO>();
        List<HouseWebDTO> economicsHouseList = new ArrayList<HouseWebDTO>();
        List<HouseWebDTO> comfortableHouseList = new ArrayList<HouseWebDTO>();
        List<HouseWebDTO> highgradeHouseList = new ArrayList<HouseWebDTO>();
        List<HouseWebDTO> lucuryHouseList = new ArrayList<HouseWebDTO>();
        List<HouseWebDTO> houseWebDTOS = new ArrayList<>();
        if (houseEntityList != null) {
            for (HouseEntity houseEntity : houseEntityList) {
                if (houseEntity.getHouseType().equals("ECONOMICS")) {//经济房
                    HouseWebDTO economicsHouseDTO = new HouseWebDTO();
                    economicsHouseDTO.setHouseId(houseEntity.getHouseId());
                    economicsHouseDTO.setHouseType("二星级及以下/经济房");
                    economicsHouseDTO.setHouseDescribe(houseEntity.getHouseDescribe());
                    economicsHouseDTO.setHousePrice(houseEntity.getHousePrice());
                    economicsHouseDTO.setGroupBuyingPrice(houseEntity.getGroupBuyingPrice());
                    economicsHouseDTO.setHouseImge(houseEntity.getHouseImge());
                    economicsHouseList.add(economicsHouseDTO);
                }
                if (houseEntity.getHouseType().equals("COMFORTABLE")) {
                    HouseWebDTO comfortableHouseDTO = new HouseWebDTO();
                    comfortableHouseDTO.setHouseId(houseEntity.getHouseId());
                    comfortableHouseDTO.setHouseType("三星级/舒适房");
                    comfortableHouseDTO.setHouseDescribe(houseEntity.getHouseDescribe());
                    comfortableHouseDTO.setHousePrice(houseEntity.getHousePrice());
                    comfortableHouseDTO.setGroupBuyingPrice(houseEntity.getGroupBuyingPrice());
                    comfortableHouseDTO.setHouseImge(houseEntity.getHouseImge());
                    comfortableHouseList.add(comfortableHouseDTO);
                }
                if (houseEntity.getHouseType().equals("HIGH_GRADE")) {
                    HouseWebDTO highgradeHouseDTO = new HouseWebDTO();
                    highgradeHouseDTO.setHouseId(houseEntity.getHouseId());
                    highgradeHouseDTO.setHouseType("四星级/高档");
                    highgradeHouseDTO.setHouseDescribe(houseEntity.getHouseDescribe());
                    highgradeHouseDTO.setHousePrice(houseEntity.getHousePrice());
                    highgradeHouseDTO.setGroupBuyingPrice(houseEntity.getGroupBuyingPrice());
                    highgradeHouseDTO.setHouseImge(houseEntity.getHouseImge());
                    highgradeHouseList.add(highgradeHouseDTO);
                }
                if (houseEntity.getHouseType().equals("LUCURY")) {
                    HouseWebDTO lucuryHouseDTO = new HouseWebDTO();
                    lucuryHouseDTO.setHouseId(houseEntity.getHouseId());
                    lucuryHouseDTO.setHouseType("五星级/豪华房");
                    lucuryHouseDTO.setHouseDescribe(houseEntity.getHouseDescribe());
                    lucuryHouseDTO.setHousePrice(houseEntity.getHousePrice());
                    lucuryHouseDTO.setGroupBuyingPrice(houseEntity.getGroupBuyingPrice());
                    lucuryHouseDTO.setHouseImge(houseEntity.getHouseImge());
                    lucuryHouseList.add(lucuryHouseDTO);
                }
                HouseWebDTO houseWebDTO = new HouseWebDTO();
                houseWebDTO.setId(houseEntity.getHouseId());
                houseWebDTO.setImageUrl(houseEntity.getHouseImge());
                if (houseEntity.getHouseType().equals("ECONOMICS")) {
                    houseWebDTO.setTitle("二星级及以下/经济房");
                }
                if (houseEntity.getHouseType().equals("COMFORTABLE")) {
                    houseWebDTO.setTitle("三星级/舒适房");
                }
                if (houseEntity.getHouseType().equals("HIGH_GRADE")) {
                    houseWebDTO.setTitle("四星级/高档");
                }
                if (houseEntity.getHouseType().equals("LUCURY")) {
                    houseWebDTO.setTitle("五星级/豪华房");
                }
                houseWebDTO.setDescribe(houseEntity.getHouseDescribe());

                houseWebDTOS.add(houseWebDTO);
            }
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("rooms", houseWebDTOS);
        if (lucuryHouseList != null && lucuryHouseList.size() > 0) {
            modelMap.addAttribute("lucuryHouse", lucuryHouseList.get(0));
        }
        if (highgradeHouseList != null && highgradeHouseList.size() > 0) {
            modelMap.addAttribute("highgradeHouse", highgradeHouseList.get(0));
        }
        if (comfortableHouseList != null && comfortableHouseList.size() > 0) {
            modelMap.addAttribute("comfortableHouse", comfortableHouseList.get(0));
        }
        if (economicsHouseList != null && economicsHouseList.size() > 0) {
            modelMap.addAttribute("economicsHouse", economicsHouseList.get(0));
        }
        return new SuccessApiResult(modelMap);
    }

    @Override
    public ApiResult getHouseDetailInfoById(String id) {
        HouseEntity houseEntity = houseRepository.getHouseInfoById(id);
        List<HouseImageEntity> houseImageEntities = houseRepository.getHouseImageList();
        HouseDTO houseDTO = null;
        if (houseEntity != null) {
            houseDTO = new HouseDTO();
            houseDTO.setHouseId(houseEntity.getHouseId());
            if (houseEntity.getHouseType().equals("ECONOMICS")) {
                houseDTO.setHouseType("二星级及以下/经济房");
            }
            if (houseEntity.getHouseType().equals("COMFORTABLE")) {
                houseDTO.setHouseType("三星级/舒适房");
            }
            if (houseEntity.getHouseType().equals("HIGH_GRADE")) {
                houseDTO.setHouseType("四星级/高档");
            }
            if (houseEntity.getHouseType().equals("LUCURY")) {
                houseDTO.setHouseType("五星级/豪华房");
            }
            houseDTO.setHousePrice(houseEntity.getHousePrice());
            houseDTO.setGroupBuyingPrice(houseEntity.getGroupBuyingPrice());
            houseDTO.setHouseDescribe(houseEntity.getHouseDescribe());
            houseDTO.setHouseImge(houseEntity.getHouseImge());
            if (houseImageEntities != null && houseImageEntities.size() > 0) {
                List<String> list = new ArrayList<String>();
                for (HouseImageEntity houseImageEntity : houseImageEntities) {
                    if (houseImageEntity.getHouseId().equals(houseEntity.getHouseId())) {
                        String imageUrl = houseImageEntity.getImgUrl();
                        list.add(imageUrl);
                    }
                }
                houseDTO.setHouseImageList(list);
            }
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("houseInfo", houseDTO);
        return new SuccessApiResult(modelMap);
    }
}
