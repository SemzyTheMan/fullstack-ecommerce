package com.semzy_ecommerce.soft.dao;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryDAO {
    public String uploadFile(MultipartFile file, String folderName);
}
