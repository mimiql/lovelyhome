package org.csu.lovelyhome.pojo.param;

import org.csu.lovelyhome.entity.House;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zjx
 * 接收上传租房的参数信息
 */
public class HouseParam {

    private MultipartFile[] files;
    private House house;

    public MultipartFile[] getFiles() {
        return files;
    }

    public House getHouse() {
        return house;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
