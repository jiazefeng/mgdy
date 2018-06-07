package com.mgdy.vesta.application.viewSpot.inf;

import com.mgdy.vesta.application.viewSpot.DTO.ViewSpotDTO;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Jason on 2017/7/11.
 */
public interface ViewSpotService {
    /**
     * 分页查询景点信息
     *
     * @param viewSpiotDTO
     * @param webPage
     * @param userPropertystaff
     * @return
     */
    List<ViewSpotDTO> getViewSpotDTOInfoList(ViewSpotDTO viewSpiotDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff);

    /**
     * 保存景点信息
     *  @param userPropertystaffEntity
     * @param viewSpotImgFile
     * @param viewSpotDTO
     * @param req
     * @param imgType
     * @param IMAGE_SERVER_URL
     */
    void saveViewSpotInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile viewSpotImgFile, ViewSpotDTO viewSpotDTO, HttpServletRequest req, String imgType, String IMAGE_SERVER_URL);

    /**
     * 获取对应的信息
     *
     * @param viewSpotId
     * @return
     */
    ViewSpotDTO getViewSpotInfoById(String viewSpotId);

    /**
     * 编辑景点信息
     *  @param userPropertystaffEntity
     * @param viewSpotImgFile
     * @param viewSpotDTO
     * @param req
     * @param imgType
     * @param IMAGE_SERVER_URL
     */
    void updateViewSpotInfo(UserPropertyStaffEntity userPropertystaffEntity, MultipartFile viewSpotImgFile, ViewSpotDTO viewSpotDTO, HttpServletRequest req, String imgType, String IMAGE_SERVER_URL);

    /**
     * 删除景点信息
     *
     * @param viewSoptId
     */
    void deleteViewSpotInfo(String viewSoptId);

    /**
     * 获取景点信息
     *
     * @return
     */
    ApiResult getViewSpotInfo();

    ApiResult getViewSpotInfoByIdAPI(String id);
}
