package org.csu.lovelyhome.service;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    public String uploadPicture(MultipartFile file);
}
