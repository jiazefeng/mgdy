package com.mgdy.vesta.utility.ImgUpdate;

import com.mgdy.vesta.utility.DateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jason on 2017/7/4.
 */
public class FileUpload {
    static TransfMediaTool transfMediaTool = new TransfMediaTool();
    // 文件最大500M
    private static long upload_maxsize = 800 * 1024 * 1024;
    // 文件允许格式
    private static String[] allowFiles = {".rar", ".doc", ".docx", ".zip",
            ".pdf", ".txt", ".swf", ".xlsx", ".gif", ".png", ".jpg", ".jpeg",
            ".bmp", ".xls", ".mp4", ".flv", ".ppt", ".avi", ".mpg", ".wmv",
            ".3gp", ".mov", ".asf", ".asx", ".vob", ".wmv9", ".rm", ".rmvb"};
    // 允许转码的视频格式（ffmpeg）
    private static String[] allowFLV = {".avi", ".mpg", ".wmv", ".3gp",
            ".mov", ".asf", ".asx", ".vob"};
    // 允许的视频转码格式(mencoder)
    private static String[] allowAVI = {".wmv9", ".rm", ".rmvb"};

    public static String upload(HttpServletRequest req, MultipartFile file, String imgType) {
        Map<String, Object> result = new HashMap<String, Object>();
        String fileName = "";
        String realPaht = req.getSession().getServletContext().getRealPath("/");
        String returnPath = "http://211.83.242.92:8080/static/" + imgType + "/";
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

    public static String uploadFile(HttpServletRequest request, MultipartFile multipartFile) {
        boolean bflag = false;
        String fileName = multipartFile.getOriginalFilename().toString();
        if (multipartFile.getSize() > 0 && !multipartFile.isEmpty()) {
            bflag = true;
        } else {
            bflag = false;
            System.out.println("文件为空");
            return "文件为空";
        }
        if (bflag) {

            String logoPathDir = "/static/video/";
            String returnPath = "http://211.83.242.92:8080/";
            String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir);
            File logoSaveFile = new File(logoRealPathDir);
            if (!logoSaveFile.exists()) {
                logoSaveFile.mkdirs();
            }
            String name = fileName.substring(0, fileName.lastIndexOf("."));
            System.out.println("文件名称：" + name);
            // 新的文件名
            String newFileName = "video-"+DateUtils.getNow("yyyyMMddhhmmss");
            // 文件扩展名
            String fileEnd = getFileExt(fileName);
            // 绝对路径
            String fileNamedirs = logoRealPathDir + newFileName + fileEnd;
            System.out.println("保存的绝对路径：" + fileNamedirs);
            File filedirs = new File(fileNamedirs);
            // 转入文件
            try {
                multipartFile.transferTo(filedirs);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 相对路径
            String fileDir = logoPathDir + newFileName + fileEnd;
            StringBuilder builder = new StringBuilder(fileDir);
            String finalFileDir = builder.substring(1);
            System.out.println(returnPath+finalFileDir);
            return returnPath+finalFileDir;
            // size存储为String
//            String size = getSize(filedirs);
            // 源文件保存路径
//            String aviPath = filedirs.getAbsolutePath();
            // 转码Avi
//            boolean flag = false;
//            if (checkAVIType(fileEnd)) {
//                // 设置转换为AVI格式后文件的保存路径
//                String codcAviPath = logoRealPathDir + File.separator + newFileName + ".avi";
//                // 获取配置的转换工具（mencoder.exe）的存放路径
//                String mencoderPath = request.getSession().getServletContext().getRealPath("/tools/mencoder.exe");
//                aviPath = transfMediaTool.processAVI(mencoderPath, filedirs.getAbsolutePath(), codcAviPath);
//                fileEnd = getFileExt(codcAviPath);
//            }
//            if (aviPath != null) {
                // 转码Flv
//                if (checkMediaType(fileEnd)) {
//                    try {
//                        // 设置转换为flv格式后文件的保存路径
//                        String codcFilePath = logoRealPathDir + File.separator + newFileName + ".flv";
//                        // 获取配置的转换工具（ffmpeg.exe）的存放路径
//                        String ffmpegPath = request.getSession().getServletContext().getRealPath("/tools/ffmpeg.exe");
//                        transfMediaTool.processFLV(ffmpegPath, aviPath, codcFilePath);
//                        fileDir = logoPathDir + newFileName + ".flv";
//                        builder = new StringBuilder(fileDir);
//                        finalFileDir = builder.substring(1);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
////                entity.setSize(size);
////                entity.setPath(finalFileDir);
////                entity.setTitleOrig(name);
////                entity.setTitleAlter(newFileName);
////                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
////                entity.setUploadTime(timestamp);
//                return finalFileDir;
//            } else {
//                return null;
//            }

        } else {
            return null;
        }

    }

    /**
     * 文件类型判断
     *
     * @param fileName
     * @return
     */
    private boolean checkFileType(String fileName) {
        Iterator<String> type = Arrays.asList(allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 视频类型判断(flv)
     *
     * @param fileEnd
     * @return
     */
    private static boolean checkMediaType(String fileEnd) {
        Iterator<String> type = Arrays.asList(allowFLV).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileEnd.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 视频类型判断(AVI)
     *
     * @param fileEnd
     * @return
     */
    private static boolean checkAVIType(String fileEnd) {
        Iterator<String> type = Arrays.asList(allowAVI).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileEnd.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件扩展名
     *
     * @return string
     */
    private static String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 依据原始文件名生成新文件名
     *
     * @return
     */
    private static String getName(String fileName) {
        Iterator<String> type = Arrays.asList(allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.contains(ext)) {
                String newFileName = fileName.substring(0, fileName.lastIndexOf(ext));
                return newFileName;
            }
        }
        return "";
    }

    /**
     * 文件大小，返回kb.mb
     *
     * @return
     */
    private static String getSize(File file) {
        String size = "";
        long fileLength = file.length();
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength < 1024) {
            size = df.format((double) fileLength) + "BT";
        } else if (fileLength < 1048576) {
            size = df.format((double) fileLength / 1024) + "KB";
        } else if (fileLength < 1073741824) {
            size = df.format((double) fileLength / 1048576) + "MB";
        } else {
            size = df.format((double) fileLength / 1073741824) + "GB";
        }
        return size;
    }
}
