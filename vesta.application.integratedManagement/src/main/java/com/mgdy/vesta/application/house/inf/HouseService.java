package com.mgdy.vesta.application.house.inf;

import com.mgdy.vesta.application.house.DTO.HouseDTO;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jason on 2017/7/10.
 */
public interface HouseService {
    /**
     * 分页获取房屋信息列表
     *
     * @param houseDTO
     * @param webPage
     * @param userPropertystaff
     * @return
     */
    List<HouseDTO> getHouseInfoList(HouseDTO houseDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff);

    /**
     * 保存房屋信息
     *
     * @param userPropertystaffEntity
     * @param homePageimgFile
     * @param houseDetailImages
     * @param houseDTO
     */
    void saveHouseInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile homePageimgFile, MultipartFile[] houseDetailImages, HouseDTO houseDTO, HttpServletRequest req, String imgType);

    /**
     * 按ID获取信息
     *
     * @param houseId
     * @return
     */
    HouseDTO getHouseInfoById(String houseId);

    /**
     * 修改房屋信息
     *
     * @param userPropertystaffEntity
     * @param homePageimgFile
     * @param houseDetailImages
     * @param houseDTO
     * @param req
     * @param imgType
     */
    void updateHouseInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile homePageimgFile, MultipartFile[] houseDetailImages, HouseDTO houseDTO, HttpServletRequest req, String imgType);

    /**
     * 删除房屋信息
     *
     * @param houseId
     */
    void deleteHouseInfo(String houseId);
//----------------------------------前端接口---------------------------------//

    /**
     * 获取房屋信息
     *
     * @return
     */
    ApiResult getHouseInfoList();

    /**
     * 根据ID获取房屋详细信息
     *
     * @param id
     * @return
     */
    ApiResult getHouseDetailInfoById(String id);
}
