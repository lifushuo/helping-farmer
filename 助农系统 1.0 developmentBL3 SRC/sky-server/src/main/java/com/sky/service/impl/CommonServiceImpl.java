package com.sky.service.impl;

import com.sky.result.Result;
import com.sky.service.CommonService;
import com.sky.utils.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    AliOssUtil aliOssUtil;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @Override
    public Result upload(MultipartFile file) {
        String url = null;
        try {
            //获取原始文件名
            String originalFilename = file.getOriginalFilename();
            //获取文件后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName = UUID.randomUUID().toString() + extension;


            //文件的请求路径
            url = aliOssUtil.upload(file.getBytes(), objectName);
        } catch (IOException e) {
            return Result.error(e.getMessage());
        }
        if (url == null) {
            return Result.error("上传失败");
        }
        return Result.success(url);
    }
}
