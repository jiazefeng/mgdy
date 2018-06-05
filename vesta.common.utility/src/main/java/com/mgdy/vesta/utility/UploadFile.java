package com.mgdy.vesta.utility;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Jiazefeng on 2018/1/13.
 */
public class UploadFile {
    public static String imgUpload(MultipartFile file, String imageServerUrl) {
        try {
            // 接收上传的文件
            // 取扩展名
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            // 上传到图片服务器
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:client.conf");
            String url = fastDFSClient.uploadFile(file.getBytes(), extName);
            url = imageServerUrl + url;
            // 响应上传图片的url
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
