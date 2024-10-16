package com.sky.service;


import com.sky.result.Result;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
    Result upload(MultipartFile file);
}
