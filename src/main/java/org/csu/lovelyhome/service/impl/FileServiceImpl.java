package org.csu.lovelyhome.service.impl;

import com.aliyun.oss.*;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.csu.lovelyhome.config.ConstantConfig;
import org.csu.lovelyhome.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class FileServiceImpl  implements FileService {

    @Autowired
    private ConstantConfig constantConfig;
//    private String FILEISNULL = "Sorry,file is null";
//    private String UPLOADPICTUREFAIL = "Sorry,upload picture fail";

    @Override
    public String uploadPicture(MultipartFile file) {
        if (file == null){
            return null;
        }
        OSS client =new OSSClientBuilder().build(constantConfig.getENDPOINT(),constantConfig.getACCESSKEYID(),constantConfig.getACCESSKEYSECRET());
        try {
            String bucketName = constantConfig.getBUCKETNAME();
            String fileHost = constantConfig.getFILEHOST();
            //判断容器是否存在，不存在则创建
            if (!client.doesBucketExist(bucketName)) {
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }
            //设置文件路径及名称+ ("/" + UUID.randomUUID().toString().replace("-", "") + "-" )
            String fileUrl = fileHost + "/" + file.getOriginalFilename();
            System.out.println("file size:" + file.getSize());
            //上传文件
            PutObjectResult result = client.putObject(bucketName, fileUrl,  new ByteArrayInputStream(file.getBytes()));
             //设置权限(公开读)
//            client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result == null){
                return null;
            }
            else {
                return fileUrl;
            }
        }catch (OSSException oe){
            oe.getMessage();
        }catch (ClientException ce){
            ce.getMessage();
        }catch (IOException io){
            io.printStackTrace();
        }finally {
            if (client != null){
                client.shutdown();
            }
        }

        return null;
    }
}
