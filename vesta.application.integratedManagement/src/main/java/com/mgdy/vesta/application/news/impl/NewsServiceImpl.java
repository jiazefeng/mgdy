package com.mgdy.vesta.application.news.impl;

import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.setting.ImageConfig;
import com.mgdy.vesta.application.news.DTO.NewsDTO;
import com.mgdy.vesta.application.news.DTO.NewsWebDTO;
import com.mgdy.vesta.application.news.inf.NewsService;
import com.mgdy.vesta.common.restHTTPResult.ApiResult;
import com.mgdy.vesta.common.restHTTPResult.SuccessApiResult;
import com.mgdy.vesta.domain.model.UserPropertyStaffEntity;
import com.mgdy.vesta.domain.news.model.NewsEntity;
import com.mgdy.vesta.domain.news.repository.NewsRepository;
import com.mgdy.vesta.taglib.page.WebPage;
import com.mgdy.vesta.utility.DateUtils;
import com.mgdy.vesta.utility.IdGen;
import com.mgdy.vesta.utility.ImgUpdate.FileUpload;
import com.mgdy.vesta.utility.ImgUpdate.ImgType;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Jason on 2017/7/4.
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    //    @Autowired
//    ImgService imgService;
    @Override
    public List<NewsDTO> getNewsList(NewsDTO newsDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff) {
        Map map = new HashMap();
        List<NewsEntity> newsEntityList = newsRepository.getNewsList(map, webPage, userPropertystaff.getStaffId());
        List<NewsDTO> newsDTOS = new ArrayList<NewsDTO>();
        if (newsEntityList != null && newsEntityList.size() > 0) {
            for (NewsEntity newsEntity : newsEntityList) {
                NewsDTO newsDTO1 = new NewsDTO();
                newsDTO1.setNewsId(newsEntity.getNewsId());
                newsDTO1.setNewsTitle(newsEntity.getNewsTitle());
                newsDTO1.setNewsSource(newsEntity.getNewsSource());
                newsDTO1.setOrder(newsEntity.getOrder());
                newsDTO1.setCreateName(newsEntity.getCreateName());
                newsDTO1.setSlideShow(newsEntity.getSlideShow());
                newsDTO1.setCreateDate(DateUtils.format(newsEntity.getCreateDate(), DateUtils.FORMAT_LONG));
                newsDTO1.setLatitude(newsEntity.getLatitude());
                newsDTO1.setLongitude(newsEntity.getLongitude());
                newsDTOS.add(newsDTO1);
            }
        }
        return newsDTOS;
    }

    @Override
    public void addNews(NewsDTO newsDTO, MultipartFile newsImgFile, UserPropertyStaffEntity userPropertystaffEntity, HttpServletRequest req, String imgType) {
        if (newsDTO != null) {
            NewsEntity newsEntity = new NewsEntity();
            newsEntity.setNewsId(IdGen.uuid());
            newsEntity.setNewsTitle(newsDTO.getNewsTitle());
            newsEntity.setNewsSource(newsDTO.getNewsSource());
            newsEntity.setNewsContent(newsDTO.getNewsContent());
            newsEntity.setLatitude(newsDTO.getLatitude());
            newsEntity.setLongitude(newsDTO.getLongitude());
            newsEntity.setCreateDate(new Date());
            newsEntity.setCreateName(userPropertystaffEntity.getStaffName());
            newsEntity.setModifyDate(new Date());
            newsEntity.setModifyName(userPropertystaffEntity.getStaffName());
            newsEntity.setSlideShow("0");
            //设置信息标识图
            if (null != newsImgFile) {
                String newsImgUrl = FileUpload.upload(req, newsImgFile, imgType);
//                if (newsImgUrl.lastIndexOf("/") != newsImgUrl.length()-1){
                newsEntity.setNewsImgUrl(newsImgUrl);
//                }
            }
            newsRepository.addNews(newsEntity);
        }
    }

    @Override
    public void uploadImage(MultipartFile file, HttpServletRequest request, String imageType, HttpServletResponse response) {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        try {
            LinkedList<MultipartFile> upfileMap = (LinkedList<MultipartFile>) ((DefaultMultipartHttpServletRequest) request).getMultiFileMap().get("upfile");
            for (MultipartFile tempfile : upfileMap) {
                //图片上传
                String fileName = FileUpload.upload(request, tempfile, imageType);
//                String fileName = imgService.uploadAdminImage(tempfile, ImgType.ACTIVITY);
//                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                String fileNameNote = fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
//                fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                resultmap.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
                resultmap.put("url", fileName);
                resultmap.put("size", tempfile.getSize());
                resultmap.put("title", fileNameNote);
                resultmap.put("type", fileName.substring(fileName.lastIndexOf(".")));
                resultmap.put("original", fileNameNote);
                String jsonstr = "";
                jsonstr = "{\"state\":\"" + resultmap.get("state") + "\",\"url\":\"" + resultmap.get("url") + "\",\"title\":\"" + resultmap.get("title") + "\",\"original\":\"" + resultmap.get("original") + "\"}";
                System.out.println((new StringBuilder("返回Json信息：")).append(jsonstr).toString());
                response.setContentType("text/html;charset=UTF-8");
                ServletOutputStream os;
                try {
                    os = response.getOutputStream();
                    os.write(jsonstr.getBytes("UTF-8"));
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            resultmap.put("state", "文件上传失败!");
            resultmap.put("url", "");
            resultmap.put("title", "");
            resultmap.put("original", "");
            String jsonstr = "";
            jsonstr = "{\"state\":\"" + resultmap.get("state") + "\",\"url\":\"" + resultmap.get("url") + "\",\"title\":\"" + resultmap.get("title") + "\",\"original\":\"" + resultmap.get("original") + "\"}";
            System.out.println((new StringBuilder("返回Json信息：")).append(jsonstr).toString());
            response.setContentType("text/html;charset=UTF-8");
        }
    }

    @Override
    public NewsDTO getNewsInfoById(String newsId) {
        NewsEntity newsEntity = newsRepository.getNewsInfoById(newsId);
        if (newsEntity != null) {
            NewsDTO newsDTO = new NewsDTO();
            newsDTO.setNewsId(newsEntity.getNewsId());
            newsDTO.setNewsSource(newsEntity.getNewsSource());
            newsDTO.setNewsTitle(newsEntity.getNewsTitle());
            newsDTO.setNewsImgUrl(newsEntity.getNewsImgUrl());
            newsDTO.setNewsContent(newsEntity.getNewsContent());
            newsDTO.setSlideShow(newsEntity.getSlideShow());
            newsDTO.setLatitude(newsEntity.getLatitude());
            newsDTO.setLongitude(newsEntity.getLongitude());
            return newsDTO;
        }
        return null;
    }

    @Override
    public void updateNews(NewsDTO newsDTO, MultipartFile newsImgFile, UserPropertyStaffEntity userPropertystaffEntity, HttpServletRequest req, String imgType) {

        NewsEntity newsEntity = newsRepository.getNewsInfoById(newsDTO.getNewsId());
        if (newsEntity != null) {
            newsEntity.setNewsTitle(newsDTO.getNewsTitle());
            newsEntity.setNewsSource(newsDTO.getNewsSource());
            newsEntity.setNewsContent(newsDTO.getNewsContent());
            newsEntity.setLatitude(newsDTO.getLatitude());
            newsEntity.setLongitude(newsDTO.getLongitude());
            newsEntity.setModifyDate(new Date());
            newsEntity.setModifyName(userPropertystaffEntity.getStaffName());
            if (null != newsImgFile) {
                String newsImgUrl = FileUpload.upload(req, newsImgFile, imgType);
                newsEntity.setNewsImgUrl(newsImgUrl);
            }
            //设置信息标识图
            newsRepository.updateNews(newsEntity);
        }
    }

    @Override
    public void deleteNews(String newsId) {
        NewsEntity newsEntity = newsRepository.getNewsInfoById(newsId);
        if (newsEntity != null) {
            newsRepository.deleteNews(newsEntity);
        }
    }

    @Override
    public boolean topNews(NewsDTO newsDTO, UserPropertyStaffEntity userPropertystaffEntity) {
        NewsEntity newsEntity = newsRepository.getNewsInfoById(newsDTO.getNewsId());
        String slideShow = "0";
        int count = newsRepository.getNewsCount(slideShow);
        if (count >= 5) {
            return false;
        } else {
            newsEntity.setSlideShow("1");
            newsEntity.setModifyDate(new Date());
            newsEntity.setModifyName(userPropertystaffEntity.getStaffName());
            newsRepository.updateNews(newsEntity);
            return true;
        }
    }

    @Override
    public boolean cancelTopNews(NewsDTO newsDTO, UserPropertyStaffEntity userPropertystaffEntity) {
        NewsEntity newsEntity = newsRepository.getNewsInfoById(newsDTO.getNewsId());
        if (newsEntity != null) {
            newsEntity.setModifyDate(new Date());
            newsEntity.setModifyName(userPropertystaffEntity.getStaffName());
            newsEntity.setSlideShow("0");
            newsRepository.updateNews(newsEntity);
            return true;
        }
        return false;
    }

    @Override
    public ApiResult getWebNewsList() {
        List<NewsEntity> newsEntityList = newsRepository.getNewsList();
        List<NewsWebDTO> newsWebDTOS = new ArrayList<NewsWebDTO>();
        List<NewsWebDTO> slideShowDTOS = new ArrayList<NewsWebDTO>();
        List<NewsWebDTO> grasslands = new ArrayList<NewsWebDTO>();
        if (newsEntityList != null) {
            for (NewsEntity newsEntity : newsEntityList) {
                NewsWebDTO newsWebDTO = new NewsWebDTO();
                newsWebDTO.setNewsId(newsEntity.getNewsId());
                newsWebDTO.setNewsTitle(newsEntity.getNewsTitle());
                newsWebDTO.setNewsImgUrl(newsEntity.getNewsImgUrl());
                newsWebDTO.setNewsContent(newsEntity.getNewsContent());
                newsWebDTO.setCreateDate(DateUtils.format(newsEntity.getCreateDate(), "yyyy-MM-dd"));
                if (newsEntity.getSlideShow().equals("1")) {
                    NewsWebDTO newsWebDTO1 = new NewsWebDTO();
                    newsWebDTO1.setNewsId(newsEntity.getNewsId());
                    newsWebDTO1.setNewsTitle(newsEntity.getNewsTitle());
                    newsWebDTO1.setNewsImgUrl(newsEntity.getNewsImgUrl());
                    newsWebDTO1.setNewsContent(newsEntity.getNewsContent());
                    slideShowDTOS.add(newsWebDTO1);
                } else {
                    NewsWebDTO newsWebDTO2 = new NewsWebDTO();
                    newsWebDTO2.setId(newsEntity.getNewsId());
                    newsWebDTO2.setTitle(newsEntity.getNewsTitle());
                    newsWebDTO2.setImageUrl(newsEntity.getNewsImgUrl());
                    newsWebDTO2.setSummary(newsEntity.getNewsSource());
                    grasslands.add(newsWebDTO2);
                }
                newsWebDTOS.add(newsWebDTO);
            }
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("newsList", newsWebDTOS);
        modelMap.addAttribute("grasslands", grasslands);
        modelMap.addAttribute("slideShowList", slideShowDTOS);
        return new SuccessApiResult(modelMap);
    }

    @Override
    public ApiResult getWebNewsInfoById(String id) {
        NewsEntity newsEntity = newsRepository.getNewsInfoById(id);
        NewsWebDTO newsWebDTO = new NewsWebDTO();
        ;
        if (newsEntity != null) {
            newsWebDTO.setNewsId(newsEntity.getNewsId());
            newsWebDTO.setNewsTitle(newsEntity.getNewsTitle());
            newsWebDTO.setNewsImgUrl(newsEntity.getNewsImgUrl());
            newsWebDTO.setNewsContent(newsEntity.getNewsContent());
            newsWebDTO.setCreateDate(DateUtils.format(newsEntity.getCreateDate(), "yyyy"));
            newsWebDTO.setCreateTime(DateUtils.format(newsEntity.getCreateDate(), "MM-dd"));

            newsWebDTO.setId(newsEntity.getNewsId());
            newsWebDTO.setTitle(newsEntity.getNewsTitle());
            newsWebDTO.setImageUrl(newsEntity.getNewsImgUrl());
            newsWebDTO.setContent(newsEntity.getNewsContent());
            newsWebDTO.setLatitude(newsEntity.getLatitude());
            newsWebDTO.setLongitude(newsEntity.getLongitude());
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("newsInfo", newsWebDTO);
        modelMap.addAttribute("dataDetail", newsWebDTO);
        return new SuccessApiResult(modelMap);
    }
}
