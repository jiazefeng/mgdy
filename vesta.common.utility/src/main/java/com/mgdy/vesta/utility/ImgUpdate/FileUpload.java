package com.mgdy.vesta.utility.ImgUpdate;

import com.mgdy.vesta.utility.DateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason on 2017/7/4.
 */
public class FileUpload {

    public static String upload(HttpServletRequest req, MultipartFile file, String imgType) {
        Map<String, Object> result = new HashMap<String, Object>();
        String fileName = "";
        String realPaht = req.getSession().getServletContext().getRealPath("/");
        String returnPath = "http://211.83.242.102:8080/static/" + imgType + "/";
        try {
            fileName = file.getOriginalFilename();
            //如果服务器已经存在和上传文件同名的文件，则输出提示信息    
//            File tempFile = new File(realPaht + fileName);
//            if (tempFile.exists()) {
//                boolean delResult = tempFile.delete();
//                System.out.println("删除已存在的文件：" + delResult);
//            }
            if (!StringUtils.isEmpty(fileName)) {
                String oosFilePath1 = "";
                //把读到的图片流保存成一个image
                BufferedImage image = ImageIO.read(file.getInputStream());
                //根据时间戳生成图片名称
                oosFilePath1 = DateUtils.getNow("yyyyMMddhhmmss");
                fileName = oosFilePath1 + "_" + fileName;
                String path = realPaht + "/static/" + imgType + "/" + fileName;
                //将图片写进image
                ImageIO.write(image, "png", new File(path));
                return returnPath + fileName;
            } else {
                throw new IOException("文件名为空!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.put("state", "文件上传失败!");
            result.put("url", "");
            result.put("title", "");
            result.put("original", "");
            System.out.println("文件 " + fileName + " 上传失败!");
        }
        return "";
    }
}
