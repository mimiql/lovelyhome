package org.csu.lovelyhome.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 *  用于文件上传
 *  Created by zjx on 2019/8/31
 */
public class UploadUtil {

    public static boolean save(MultipartFile file , String destinationPath){
        if (file.isEmpty()) {
            return false;
        }

        File destination = new File(destinationPath + file.getOriginalFilename());
        try {
            if(!destination.exists() && !destination.isDirectory()){
                destination.mkdirs();
            }
            file.transferTo(destination.getAbsoluteFile());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
