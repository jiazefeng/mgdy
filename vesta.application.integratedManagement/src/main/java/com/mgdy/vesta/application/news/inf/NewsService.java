package com.mgdy.vesta.application.news.inf;

import com.mgdy.vesta.application.news.DTO.NewsDTO;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Jason on 2017/7/4.
 */
public interface NewsService {
    /**
     * 分页获取新闻列表信息
     *
     * @param newsDTO
     * @param webPage
     * @param userPropertystaff
     * @return
     */
    List<NewsDTO> getNewsList(NewsDTO newsDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff);

    /**
     * 发布新闻
     *
     * @param newsDTO
     * @param newsImgFile
     * @param userPropertystaffEntity
     */
    void addNews(NewsDTO newsDTO, MultipartFile newsImgFile, UserPropertyStaffEntity userPropertystaffEntity, HttpServletRequest req, String imgType);

    /**
     * 上传图片（对富文本编辑器做处理）
     *
     * @param file
     * @param request
     */
    void uploadImage(MultipartFile file, HttpServletRequest request, String imageType, HttpServletResponse response);

    /**
     * 根据ID查询详情
     *
     * @param newsId
     * @return
     */
    NewsDTO getNewsInfoById(String newsId);

    /**
     * 编辑新闻
     *
     * @param newsDTO
     * @param newsImgFile
     * @param userPropertystaffEntity
     * @param req
     * @param imgType
     */
    void updateNews(NewsDTO newsDTO, MultipartFile newsImgFile, UserPropertyStaffEntity userPropertystaffEntity, HttpServletRequest req, String imgType);

    /**
     * 删除新闻
     *
     * @param newsId
     */
    void deleteNews(String newsId);

    /**
     * 置顶新闻
     *
     * @param newsDTO
     * @return
     */
    boolean topNews(NewsDTO newsDTO, UserPropertyStaffEntity userPropertystaffEntity);

    /**
     * 取消置顶
     *
     * @param newsDTO
     * @param userPropertystaffEntity
     * @return
     */
    boolean cancelTopNews(NewsDTO newsDTO, UserPropertyStaffEntity userPropertystaffEntity);

    //------------------------------------------前端网页接口开始-------------------------------------------------------------

    /**
     * 获取新闻信息
     *
     * @return
     */
    ApiResult getWebNewsList();

    /**
     * 根据ID获取新闻信息
     *
     * @param id
     * @return
     */
    ApiResult getWebNewsInfoById(String id);
}
