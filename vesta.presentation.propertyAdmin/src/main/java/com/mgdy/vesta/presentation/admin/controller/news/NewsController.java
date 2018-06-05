package com.mgdy.vesta.presentation.admin.controller.news;

import com.maxrocky.vesta.setting.ImageConfig;
import com.mgdy.vesta.Json.UploadImage;
import com.mgdy.vesta.application.news.DTO.NewsDTO;
import com.mgdy.vesta.application.news.inf.NewsService;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 新闻管理Controller
 * Created by Jason on 2017/7/4.
 */
@Controller
@RequestMapping(value = "/news")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;
    /**
     * 分页获取新闻列表
     *
     * @param userPropertystaff
     * @param newsDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/getNewsList.html", method = RequestMethod.GET)
    public String getNewsList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff, @Valid NewsDTO newsDTO, Model model, WebPage webPage) {

        List<NewsDTO> newsDTOS = newsService.getNewsList(newsDTO, webPage, userPropertystaff);
        model.addAttribute("newsInfoList", newsDTOS);
        model.addAttribute("newsInfo", newsDTO);
        model.addAttribute("typeMaps", newsDTO);
        return "/integratedManagement/news/NewsList";
    }

    /**
     * 跳转到新增页面
     */
    @RequestMapping(value = "/toAddNews.html")
    public String toAddNews(Model model) {
        return "/integratedManagement/news/AddNews";
    }

    /**
     * 新增新闻
     *
     * @param userPropertystaffEntity
     * @param newsImgFile
     * @param newsDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/addNews.html", method = RequestMethod.POST)
    public String addNews(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                          @RequestParam(value = "newsImgFile", required = false) MultipartFile newsImgFile,
                          NewsDTO newsDTO, Model model, HttpServletRequest req) {
        String imgType = "newsImage";
        newsService.addNews(newsDTO, newsImgFile, userPropertystaffEntity, req, imgType,IMAGE_SERVER_URL);
        return "redirect:../news/getNewsList.html";
    }

    /**
     * 上传图片（富文本编辑器）
     * Created by Jason on 2017/7/4.
     */
    @RequestMapping(value = "/uploadImage/{imgType}")
    @ResponseBody
    public void createMerPicture(@PathVariable("imgType") String imgType, @RequestParam(value = "file", required = false) MultipartFile file,
                                 Model model, HttpServletRequest request, HttpServletResponse response) {
        String imageType = imgType;
        newsService.uploadImage(file, request, imageType, response,IMAGE_SERVER_URL);
    }

    /**
     * 跳转到编辑页面
     */
    @RequestMapping(value = "/toModifyNews.html")
    public String toModifyNews(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, Model model, @RequestParam String newsId) {
        NewsDTO newsDTO = newsService.getNewsInfoById(newsId);
        model.addAttribute("newsInfo", newsDTO);
        return "/integratedManagement/news/ModifyNews";
    }

    /**
     * 编辑新闻
     *
     * @param userPropertystaffEntity
     * @param newsImgFile
     * @param newsDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateNews.html", method = RequestMethod.POST)
    public String updateNews(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                             @RequestParam(value = "newsImgFile", required = false) MultipartFile newsImgFile,
                             NewsDTO newsDTO, Model model, HttpServletRequest req) {
        String imgType = "newsImage";
        newsService.updateNews(newsDTO, newsImgFile, userPropertystaffEntity, req, imgType,IMAGE_SERVER_URL);
        return "redirect:../news/getNewsList.html";
    }

    /**
     * 删除新闻
     *
     * @return
     */
    @RequestMapping(value = "/deleteNews.html")
    public String deleteNews(@RequestParam String newsId, Model model) {
        newsService.deleteNews(newsId);
        return "redirect:../news/getNewsList.html";
    }

    /**
     * 置顶新闻
     *
     * @param userPropertystaffEntity
     * @param newsDTO
     * @return
     */
    @RequestMapping(value = "/toTopNews")
    public Map<String, Object> toTopNews(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                         NewsDTO newsDTO) {
        Map<String, Object> resultMap = new HashedMap();
        boolean flag = newsService.topNews(newsDTO, userPropertystaffEntity);
        if (flag) {
            resultMap.put("error", 0);
        } else {
            resultMap.put("error", -1);
        }
        return resultMap;
    }

    /**
     * 取消置顶新闻
     *
     * @param userPropertystaffEntity
     * @param newsDTO
     * @return
     */
    @RequestMapping(value = "/cancelTopNews")
    public Map<String, Object> cancelTopNews(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, NewsDTO newsDTO, Model model) {
        Map<String, Object> resultMap = new HashedMap();
        boolean flag = newsService.cancelTopNews(newsDTO, userPropertystaffEntity);
        if (flag) {
            resultMap.put("error", 0);
        } else {
            resultMap.put("error", -1);
        }
        return resultMap;
//        return "redirect:../news/getNewsList.html";
    }
}
