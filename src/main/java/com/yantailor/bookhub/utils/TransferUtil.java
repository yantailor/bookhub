package com.yantailor.bookhub.utils;


import com.yantailor.bookhub.bean.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;


/**
 * Created by yantailor
 * on 2022/2/5 12:12 @Version 1.0
 */

@Component

public class TransferUtil {

    public static final Logger logger = LoggerFactory.getLogger(TransferUtil.class);

    @Value("${bookhub.desPath}")
    private String desPath;

    /**
     * @Author: yantailor
     * @Description: 文件二进制传输类,单个
     * @Date: 12:14 2022/2/5
     * @Param: multipartFile 文件
     * @param: sourceDir 文件存放位置
     */

    public R TransferFile(MultipartFile multipartFile , String sourceDir){
        int readLen = 0;
        byte[] bytes = new byte[1024];
        //判断文件夹是否存在不存在则创建
        File dir = new File(desPath + "/" + sourceDir );
        logger.info("dir:"+dir);
        if(!dir.exists()){
            dir.mkdirs();
            logger.info("创建文件夹:"+dir);
        }

        try {
            InputStream stream = multipartFile.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(stream);
            System.out.println();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(desPath+"/"+sourceDir+"/"+multipartFile.getOriginalFilename())));
            while ((readLen = bis.read(bytes)) != -1){
                bos.write(bytes, 0, readLen);
                bos.flush();
            }
            bis.close();
            bos.close();

        } catch (IOException e) {
            logger.info("单文件传输失败----,fileName:"+multipartFile.getOriginalFilename()+"  sourceDir:"+sourceDir+"    "+new Date());
            e.printStackTrace();
        }
        logger.info("单文件传输成功++++,fileName:"+multipartFile.getOriginalFilename()+"  sourceDir:"+sourceDir+"    "+new Date());
        return R.ok();
    }


    /**
     * @Author: yantailor
     * @Description: 文件二进制传输类,多个个
     * @Date: 12:14 2022/2/5
     * @Param: multipartFile 文件
     * @param:  sourceDir 文件存放位置
     */
    public R TransferFiles(MultipartFile[] multipartFile , String sourceDir){
        try {
            for (int i=0 ; i < multipartFile.length ; i++){
                TransferFile(multipartFile[i],sourceDir);
            }
        } catch (Exception e) {
            logger.info("多文件传输失败----");
            e.printStackTrace();
        }

        logger.info("多文件上传成功++++");
        return R.ok();
    }


    /**
     * @Author: yantailor
     * @Description: 删除文件,多个
     * @Date: 15:49 2022/2/10
     * @param: List<String> fileUrl
     */
    public void RemoveFiles(List<String> fileUrls){
        try {
            for (int i = 0 ; i < fileUrls.size() ; i++){
                File file = new File(fileUrls.get(i));
                if(file.exists()){
                    file.delete();
                    logger.info("文件删除成功++++,fileUrl:"+fileUrls+"    "+new Date());
                }
            }
        } catch (Exception e) {
            logger.info("文件删除失败----");
            e.printStackTrace();
        }
    }

    /**
     * @Author: yantailor
     * @Description: 删除文件,多个
     * @Date: 15:49 2022/2/10
     * @param: String fileUrl
     */
    public void RemoveFile(String fileUrl){
        try {
            File file = new File(fileUrl);
            if(file.exists()){
                file.delete();
                logger.info("文件删除成功++++,fileUrl:"+fileUrl+"    "+new Date());
        }
        } catch (Exception e) {
            logger.info("文件删除失败----");
            e.printStackTrace();
        }
    }




}
