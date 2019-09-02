package org.csu.lovelyhome.service;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {
    String uploadPicture(MultipartFile file);
}
